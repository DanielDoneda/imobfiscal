from datetime import date
import unittest

from app.analytics import calcular_resumo, resumo_em_linhas


class AnalyticsTest(unittest.TestCase):
    def test_calcula_indicadores_gerenciais(self) -> None:
        imoveis = [{"id": "i1"}, {"id": "i2"}, {"id": "i3"}]
        locadores = [{"id": "l1"}, {"id": "l2"}]
        contratos = [
            {
                "id": "c1",
                "imovelId": "i1",
                "status": "ATIVO",
                "valorAluguel": 1500,
                "dataFim": "2026-11-30",
            },
            {
                "id": "c2",
                "imovelId": "i2",
                "status": "ATIVO",
                "valorAluguel": 2000,
                "dataFim": "2027-05-01",
            },
            {"id": "c3", "imovelId": "i3", "status": "ENCERRADO", "valorAluguel": 900},
        ]
        boletos = [
            {"status": "PAGO", "valorLiquido": 1500, "dataVencimento": "2026-11-10"},
            {"status": "PENDENTE", "valorLiquido": 2000, "dataVencimento": "2026-11-25"},
            {"status": "PENDENTE", "valorLiquido": 800, "dataVencimento": "2026-11-01"},
        ]

        resumo = calcular_resumo(imoveis, locadores, contratos, boletos, date(2026, 11, 21))

        self.assertEqual(3, resumo["imoveis"]["total"])
        self.assertEqual(2, resumo["imoveis"]["ocupados"])
        self.assertEqual(66.67, resumo["imoveis"]["taxaOcupacaoPercentual"])
        self.assertEqual(2, resumo["contratos"]["ativos"])
        self.assertEqual(1, resumo["contratos"]["vencendoEm30Dias"])
        self.assertEqual(3500.0, resumo["contratos"]["receitaMensalPrevista"])
        self.assertEqual(1, resumo["boletos"]["pagos"])
        self.assertEqual(1, resumo["boletos"]["pendentes"])
        self.assertEqual(1, resumo["boletos"]["vencidos"])
        self.assertEqual(2000.0, resumo["boletos"]["valorPendente"])
        self.assertEqual(800.0, resumo["boletos"]["valorVencido"])

    def test_retorna_zeros_quando_nao_ha_dados(self) -> None:
        resumo = calcular_resumo([], [], [], [], date(2026, 11, 21))

        self.assertEqual(0, resumo["imoveis"]["total"])
        self.assertEqual(0.0, resumo["imoveis"]["taxaOcupacaoPercentual"])
        self.assertEqual(0.0, resumo["contratos"]["receitaMensalPrevista"])
        self.assertGreater(len(resumo_em_linhas(resumo)), 5)


if __name__ == "__main__":
    unittest.main()

