from __future__ import annotations

import csv
import io
from uuid import UUID

from fastapi import FastAPI, Header, HTTPException, status
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import Response

from .analytics import calcular_resumo, resumo_em_linhas
from .backend_client import ImobFiscalBackendClient


app = FastAPI(
    title="ImobFiscal Relatórios",
    description="Serviço Python de indicadores gerenciais integrado à API Java do ImobFiscal.",
    version="1.0.0",
)
app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:5173", "https://pi-imob-fiscal.vercel.app"],
    allow_credentials=False,
    allow_methods=["GET"],
    allow_headers=["Authorization", "Content-Type"],
)


def token_obrigatorio(authorization: str | None) -> str:
    if not authorization or not authorization.startswith("Bearer "):
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Informe o JWT no cabeçalho Authorization: Bearer <token>",
        )
    return authorization


async def obter_resumo(imobiliaria_id: UUID, authorization: str | None) -> dict:
    token = token_obrigatorio(authorization)
    dados = await ImobFiscalBackendClient().carregar_dados(imobiliaria_id, token)
    return calcular_resumo(
        dados["imoveis"],
        dados["locadores"],
        dados["contratos"],
        dados["boletos"],
    )


@app.get("/health")
def health() -> dict[str, str]:
    return {"status": "ok", "servico": "imobfiscal-relatorios-python"}


@app.get("/api/relatorios/{imobiliaria_id}/resumo")
async def resumo(
    imobiliaria_id: UUID,
    authorization: str | None = Header(default=None),
) -> dict:
    return await obter_resumo(imobiliaria_id, authorization)


@app.get("/api/relatorios/{imobiliaria_id}/resumo.csv")
async def resumo_csv(
    imobiliaria_id: UUID,
    authorization: str | None = Header(default=None),
) -> Response:
    relatorio = await obter_resumo(imobiliaria_id, authorization)
    arquivo = io.StringIO(newline="")
    escritor = csv.writer(arquivo, delimiter=";")
    escritor.writerow(["Indicador", "Valor"])
    escritor.writerows(resumo_em_linhas(relatorio))
    conteudo = "\ufeff" + arquivo.getvalue()
    return Response(
        content=conteudo,
        media_type="text/csv; charset=utf-8",
        headers={"Content-Disposition": "attachment; filename=imobfiscal-resumo.csv"},
    )

