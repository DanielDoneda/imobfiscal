from __future__ import annotations

from datetime import date, timedelta
from decimal import Decimal, InvalidOperation
from typing import Any, Iterable


def _decimal(value: Any) -> Decimal:
    try:
        return Decimal(str(value or 0))
    except (InvalidOperation, ValueError, TypeError):
        return Decimal("0")


def _date(value: Any) -> date | None:
    if not value:
        return None
    try:
        return date.fromisoformat(str(value))
    except ValueError:
        return None


def _status(value: Any) -> str:
    return str(value or "").strip().upper()


def calcular_resumo(
    imoveis: Iterable[dict[str, Any]],
    locadores: Iterable[dict[str, Any]],
    contratos: Iterable[dict[str, Any]],
    boletos: Iterable[dict[str, Any]],
    hoje: date | None = None,
) -> dict[str, Any]:
    """Calcula indicadores sem alterar os dados recebidos da API Java."""

    hoje = hoje or date.today()
    imoveis = list(imoveis)
    locadores = list(locadores)
    contratos = list(contratos)
    boletos = list(boletos)

    contratos_ativos = [c for c in contratos if _status(c.get("status")) == "ATIVO"]
    imoveis_ocupados = {
        str(c.get("imovelId"))
        for c in contratos_ativos
        if c.get("imovelId") is not None
    }
    total_imoveis = len(imoveis)
    total_ocupados = len(imoveis_ocupados)
    taxa_ocupacao = (
        Decimal(total_ocupados) / Decimal(total_imoveis) * Decimal("100")
        if total_imoveis
        else Decimal("0")
    )

    receita_mensal = sum(
        (_decimal(c.get("valorAluguel")) for c in contratos_ativos),
        start=Decimal("0"),
    )

    limite = hoje + timedelta(days=30)
    contratos_vencendo = [
        c
        for c in contratos_ativos
        if (fim := _date(c.get("dataFim"))) is not None and hoje <= fim <= limite
    ]

    boletos_pagos = [b for b in boletos if _status(b.get("status")) == "PAGO"]
    boletos_vencidos = [
        b
        for b in boletos
        if _status(b.get("status")) == "VENCIDO"
        or (
            _status(b.get("status")) not in {"PAGO", "CANCELADO"}
            and (vencimento := _date(b.get("dataVencimento"))) is not None
            and vencimento < hoje
        )
    ]
    boletos_pendentes = [
        b
        for b in boletos
        if _status(b.get("status")) not in {"PAGO", "CANCELADO", "VENCIDO"}
        and b not in boletos_vencidos
    ]

    valor_pendente = sum(
        (_decimal(b.get("valorLiquido") or b.get("valorAluguel")) for b in boletos_pendentes),
        start=Decimal("0"),
    )
    valor_vencido = sum(
        (_decimal(b.get("valorLiquido") or b.get("valorAluguel")) for b in boletos_vencidos),
        start=Decimal("0"),
    )

    return {
        "geradoEm": hoje.isoformat(),
        "imoveis": {
            "total": total_imoveis,
            "ocupados": total_ocupados,
            "disponiveis": max(total_imoveis - total_ocupados, 0),
            "taxaOcupacaoPercentual": float(taxa_ocupacao.quantize(Decimal("0.01"))),
        },
        "locadores": {"total": len(locadores)},
        "contratos": {
            "total": len(contratos),
            "ativos": len(contratos_ativos),
            "vencendoEm30Dias": len(contratos_vencendo),
            "receitaMensalPrevista": float(receita_mensal.quantize(Decimal("0.01"))),
        },
        "boletos": {
            "total": len(boletos),
            "pagos": len(boletos_pagos),
            "pendentes": len(boletos_pendentes),
            "vencidos": len(boletos_vencidos),
            "valorPendente": float(valor_pendente.quantize(Decimal("0.01"))),
            "valorVencido": float(valor_vencido.quantize(Decimal("0.01"))),
        },
    }


def resumo_em_linhas(resumo: dict[str, Any]) -> list[tuple[str, str]]:
    """Converte o resumo em linhas simples para exportação CSV."""

    return [
        ("Data de referência", resumo["geradoEm"]),
        ("Imóveis cadastrados", str(resumo["imoveis"]["total"])),
        ("Imóveis ocupados", str(resumo["imoveis"]["ocupados"])),
        ("Imóveis disponíveis", str(resumo["imoveis"]["disponiveis"])),
        ("Taxa de ocupação (%)", f'{resumo["imoveis"]["taxaOcupacaoPercentual"]:.2f}'),
        ("Locadores cadastrados", str(resumo["locadores"]["total"])),
        ("Contratos ativos", str(resumo["contratos"]["ativos"])),
        ("Contratos vencendo em 30 dias", str(resumo["contratos"]["vencendoEm30Dias"])),
        ("Receita mensal prevista", f'{resumo["contratos"]["receitaMensalPrevista"]:.2f}'),
        ("Boletos pagos", str(resumo["boletos"]["pagos"])),
        ("Boletos pendentes", str(resumo["boletos"]["pendentes"])),
        ("Boletos vencidos", str(resumo["boletos"]["vencidos"])),
        ("Valor pendente", f'{resumo["boletos"]["valorPendente"]:.2f}'),
        ("Valor vencido", f'{resumo["boletos"]["valorVencido"]:.2f}'),
    ]

