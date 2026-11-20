# Riscos e responsabilidades

## Papéis da equipe

| Papel | Responsabilidades principais |
|---|---|
| Líder técnico | Priorização técnica, integração, revisão, releases e remoção de impedimentos |
| Arquiteto de sistemas | Decisões de arquitetura, segurança, contratos entre camadas e qualidade estrutural |
| Gerente de projetos | Quadro, ciclos, comunicação, evidências, IHC e entrega acadêmica |
| DBA | Modelagem, bancos, índices, migrações, segurança e evidências de dados |
| Desenvolvedor | Implementação frontend/backend, testes, correções e documentação do código |

## Matriz RACI resumida

Legenda: **R** executa, **A** responde pela aprovação, **C** é consultado e **I** é informado.

| Atividade | Líder | Arquiteto | Gerente | DBA | Desenvolvedor |
|---|---|---|---|---|---|
| Priorizar backlog | A | C | R | I | I |
| Definir arquitetura | A | R | I | C | C |
| Implementar funcionalidades | A | C | I | C | R |
| Modelar bancos | C | A | I | R | C |
| Executar testes técnicos | A | C | I | I | R |
| Testar usabilidade | C | C | A/R | I | I |
| Organizar Trello e evidências | C | I | A/R | I | I |
| Publicar versão | A/R | C | I | C | R |

## Registro de riscos

Escala: probabilidade e impacto de 1 a 5; exposição = probabilidade × impacto.

| ID | Risco | P | I | Exposição | Resposta | Responsável |
|---|---|---:|---:|---:|---|---|
| R1 | Mistura de dados entre tenants | 2 | 5 | 10 | JWT, filtro por tenant e testes 403 | Líder técnico |
| R2 | Serviço gratuito pausar ou mudar condição | 4 | 3 | 12 | Health check, documentação e plano de migração | Arquiteto |
| R3 | Segredo exposto no repositório | 2 | 5 | 10 | Variáveis de ambiente e revisão antes do commit | Líder técnico |
| R4 | MongoDB indisponível afetar operações | 2 | 4 | 8 | Auditoria desacoplada e configurável | DBA |
| R5 | Boleto confundido com emissão real | 3 | 4 | 12 | Aviso permanente e nomenclatura demonstrativa | Gerente |
| R6 | Contrato duplicar ocupação do imóvel | 4 | 4 | 16 | Regra e filtro planejados em WEB-17 | Desenvolvedor |
| R7 | Locador e locatário serem confundidos | 4 | 3 | 12 | Padronização terminológica e ajuda contextual | Arquiteto |
| R8 | Regressão sem testes do frontend | 3 | 4 | 12 | Vitest/RTL no backlog e build antes do deploy | Desenvolvedor |
| R9 | Documento final divergir do sistema | 3 | 4 | 12 | Capítulos por disciplina e revisão cruzada | Gerente |
| R10 | Falta de participantes para IHC | 3 | 3 | 9 | Recrutamento simples, dados fictícios e sessões curtas | Gerente |

## Cerimônias adaptadas

- Planejamento de ciclo: selecionar objetivo e itens prontos;
- Acompanhamento: registrar andamento, impedimentos e evidências;
- Revisão: demonstrar incremento funcional ou documental;
- Retrospectiva: registrar aprendizado e mudança para o ciclo seguinte;
- Refinamento: detalhar itens futuros sem expandir o escopo do ciclo corrente.
