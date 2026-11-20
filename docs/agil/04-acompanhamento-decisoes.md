# Acompanhamento e decisões do projeto

## Fluxo do quadro

Listas recomendadas no Trello:

1. Ideias e produto;
2. Backlog priorizado;
3. Pronto para desenvolver;
4. Em desenvolvimento;
5. Em validação;
6. Concluído.

Campos personalizados:

- Informado por;
- Designado para;
- Disciplina;
- Prioridade;
- Estimativa em pontos;
- Ciclo;
- Data de conclusão;
- Evidência.

Etiquetas: Frontend, Backend, Segurança, Banco relacional, NoSQL, UX/UI, DevOps, Testes,
Documentação, Alta, Média e Baixa.

## Linha do tempo verificável

| Data | Marco | Evidência |
|---|---|---|
| 19/10/2026 | Importação, Docker, plano Railway e Vercel | `ea799c6` a `9841e24` |
| 27/10/2026 | Menu lateral | `d90647a` |
| 31/10/2026 | Spring Security, JWT e Validation | `f364c46` |
| 19/11/2026 | Render e Neon | `6e86b2a` |
| 19/11/2026 | Auditoria MongoDB | `0ea6c32` |
| 19/11/2026 | Cadastro PF/PJ corrigido | `02237dc` |
| 19/11/2026 | Backlogs Web III e NoSQL | `91b24ef` |
| 20/11/2026 | Pacote de IHC | `3fea73a` |
| 20/11/2026 | Backlog de IHC | `04f050e` |

## Decisões registradas

| Decisão | Motivo | Consequência |
|---|---|---|
| Usar JWT stateless | Proteger API e carregar identidade/tenant | Exige chave forte e estratégia futura de revogação |
| Isolar dados por imobiliária | Evitar mistura entre contas | Toda rota de domínio valida o tenant |
| Usar Neon/PostgreSQL | Substituir serviço expirado | Persistência relacional gerenciada |
| Usar MongoDB para auditoria | Caso documental com escrita frequente | Dois bancos com responsabilidades distintas |
| Manter boleto como demonstração | Integração bancária fora do MVP | Interface deve informar o escopo acadêmico |
| Adiar módulo de locatários | Alteração estrutural de banco, API e telas | Dados permanecem no contrato no MVP atual |
| Adiar imóveis de terceiros | Preservar estabilidade da entrega | Recurso permanece priorizado no backlog |
| Separar inspeção de teste humano | Rigor acadêmico | Resultados de participantes continuam pendentes |

## Indicadores atuais

- 16 testes automatizados aprovados;
- 4 backlogs disciplinares ou de produto organizados;
- Frontend, backend, PostgreSQL e MongoDB publicados;
- 2 personas e 1 jornada documentadas;
- 1 inspeção especializada concluída;
- 1 plano de teste com participantes preparado;
- 0 problema crítico conhecido impedindo a demonstração do MVP.

## Impedimentos e respostas

| Impedimento | Resposta |
|---|---|
| Trial do Railway expirado | Migração para Render e Neon |
| Porta 8080 ocupada localmente | Encerrar processo ou selecionar outra porta |
| PowerShell bloqueou `npm.ps1` | Utilizar `npm.cmd` |
| Maven apresentou certificado PKIX | Execução temporária com parâmetros de transporte; corrigir confiança local depois |
| Render permaneceu em commit antigo | Deploy manual e validação da resposta pública |
| Documentos antigos afirmavam ausência de JWT | Marcação como históricos e criação de documentação atual |
