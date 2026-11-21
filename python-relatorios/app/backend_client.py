from __future__ import annotations

import asyncio
import os
from typing import Any
from uuid import UUID

import httpx
from fastapi import HTTPException, status


class ImobFiscalBackendClient:
    def __init__(self) -> None:
        self.base_url = os.getenv("JAVA_API_URL", "http://localhost:8080").rstrip("/")
        self.timeout = float(os.getenv("REQUEST_TIMEOUT_SECONDS", "10"))

    async def carregar_dados(
        self, imobiliaria_id: UUID, authorization: str
    ) -> dict[str, list[dict[str, Any]]]:
        headers = {"Authorization": authorization}
        prefixo = f"{self.base_url}/api/imobiliarias/{imobiliaria_id}"
        recursos = {
            "imoveis": f"{prefixo}/imoveis",
            "locadores": f"{prefixo}/locadores",
            "contratos": f"{prefixo}/contratos",
            "boletos": f"{prefixo}/boletos",
        }

        async with httpx.AsyncClient(timeout=self.timeout, headers=headers) as client:
            respostas = await asyncio.gather(
                *(client.get(url) for url in recursos.values()),
                return_exceptions=True,
            )

        dados: dict[str, list[dict[str, Any]]] = {}
        for (nome, _), resposta in zip(recursos.items(), respostas, strict=True):
            if isinstance(resposta, Exception):
                raise HTTPException(
                    status_code=status.HTTP_502_BAD_GATEWAY,
                    detail=f"Backend Java indisponível ao consultar {nome}",
                ) from resposta
            if resposta.status_code in {401, 403}:
                raise HTTPException(
                    status_code=resposta.status_code,
                    detail="Token inválido ou acesso não permitido para esta imobiliária",
                )
            if resposta.is_error:
                raise HTTPException(
                    status_code=status.HTTP_502_BAD_GATEWAY,
                    detail=f"Falha ao consultar {nome} no backend Java",
                )
            payload = resposta.json()
            if not isinstance(payload, list):
                raise HTTPException(
                    status_code=status.HTTP_502_BAD_GATEWAY,
                    detail=f"Resposta inesperada do backend ao consultar {nome}",
                )
            dados[nome] = payload
        return dados

