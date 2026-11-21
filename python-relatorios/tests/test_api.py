from fastapi.testclient import TestClient

from app.main import app


client = TestClient(app)


def test_health_informa_servico_ativo() -> None:
    resposta = client.get("/health")

    assert resposta.status_code == 200
    assert resposta.json() == {
        "status": "ok",
        "servico": "imobfiscal-relatorios-python",
    }


def test_resumo_exige_jwt() -> None:
    resposta = client.get(
        "/api/relatorios/11111111-1111-1111-1111-111111111111/resumo"
    )

    assert resposta.status_code == 401
    assert "JWT" in resposta.json()["detail"]
