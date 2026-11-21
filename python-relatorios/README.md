# ImobFiscal Relatórios em Python

Serviço complementar em Python para calcular indicadores gerenciais do ImobFiscal. O backend
Java continua responsável por autenticação, autorização, regras de negócio e persistência. O
serviço Python recebe o mesmo JWT e o encaminha às consultas do Java, preservando o isolamento
por imobiliária.

## Indicadores

- imóveis cadastrados, ocupados e disponíveis;
- taxa de ocupação;
- locadores cadastrados;
- contratos ativos e vencendo em 30 dias;
- receita mensal prevista;
- boletos pagos, pendentes e vencidos;
- valores pendentes e vencidos.

## Executar no Windows

No terminal, dentro da pasta `python-relatorios`:

```powershell
py -m venv .venv
.\.venv\Scripts\python.exe -m pip install -r requirements.txt
$env:JAVA_API_URL="http://localhost:8080"
.\.venv\Scripts\python.exe -m uvicorn app.main:app --reload --port 8090
```

A documentação interativa ficará disponível em:

```text
http://localhost:8090/docs
```

O backend Java deve estar rodando na porta 8080. Para outra URL, altere `JAVA_API_URL`.

## Endpoints

```text
GET /health
GET /api/relatorios/{imobiliariaId}/resumo
GET /api/relatorios/{imobiliariaId}/resumo.csv
```

Nos endpoints protegidos, envie:

```text
Authorization: Bearer <jwt-do-login>
```

Exemplo:

```powershell
$token = "TOKEN_RECEBIDO_NO_LOGIN"
$tenant = "11111111-1111-1111-1111-111111111111"
Invoke-RestMethod `
  -Uri "http://localhost:8090/api/relatorios/$tenant/resumo" `
  -Headers @{ Authorization = "Bearer $token" }
```

## Testes

Os testes do cálculo não dependem do backend nem de pacotes externos:

```powershell
py -m unittest discover -s tests -v
```

## Decisão de segurança

O serviço não confia em um tenant informado isoladamente e não tenta substituir o Spring
Security. Ele encaminha o JWT e o `imobiliariaId` ao backend Java. O backend compara o tenant da
URL com o tenant assinado no token e retorna `403` quando há divergência.

## Publicação

O `render.yaml` da raiz contém a definição do serviço `imobfiscal-relatorios`. No ambiente
online, confirme que `JAVA_API_URL` aponta para a URL real do backend Java. O frontend utiliza
`VITE_REPORTS_API_URL` quando ela estiver configurada; na ausência dela, a compilação de produção
usa `https://imobfiscal-relatorios.onrender.com`.

