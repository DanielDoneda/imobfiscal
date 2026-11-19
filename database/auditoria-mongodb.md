# Banco de dados não relacional — auditoria

O ImobFiscal utiliza MongoDB para armazenar logs de atividades dos usuarios. A escolha
documental permite acrescentar novos dados de contexto aos eventos sem alterar as tabelas
transacionais do PostgreSQL.

## Colecao `logs_atividades`

Cada documento registra: imobiliaria, usuario, e-mail, acao, metodo e rota HTTP, resultado,
endereco IP, navegador e data/hora. O indice composto `imobiliaria_data_idx` acelera a
consulta cronologica por organizacao e mantem o isolamento multiusuario.

Exemplo:

```json
{
  "imobiliariaId": "1a37bfab-cc64-48b8-9532-2f14ca3fc123",
  "usuarioId": "0264ad28-637c-4bc7-a62f-3d23d7957123",
  "usuarioEmail": "admin@imobfiscal.com.br",
  "acao": "CRIACAO",
  "metodoHttp": "POST",
  "rota": "/api/imobiliarias/1a37bfab-cc64-48b8-9532-2f14ca3fc123/imoveis",
  "statusHttp": 201,
  "enderecoIp": "203.0.113.10",
  "navegador": "Mozilla/5.0",
  "dataHora": "2026-11-19T15:00:00Z"
}
```

## Configuracao

- `MONGODB_URI`: conexao MongoDB, com usuario, senha, cluster e banco.
- `AUDIT_ENABLED=true`: ativa a persistencia dos eventos.

Sem essas variaveis, a auditoria permanece desativada e nao interfere nas operacoes do
sistema principal.
