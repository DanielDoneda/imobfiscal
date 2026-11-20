# Backlog consolidado e resumido do PI ImobFiscal

Este backlog reúne as quatro disciplinas do Projeto Integrador em poucos cartões. Ele foi preparado para cadastro no Trello e separa entregas concluídas, atividades em andamento e próximos passos.

| ID | Disciplina | Cartão | Status | Prioridade | Responsável principal | Critério de aceite |
|---|---|---|---|---|---|---|
| PI-01 | Gestão Ágil | Definir visão, público e escopo do MVP | Concluído | Alta | Gerente de Projetos | Visão, público, problema e limites do MVP documentados |
| PI-02 | Gestão Ágil | Organizar backlog, ciclos e responsáveis | Concluído | Alta | Líder Técnico | Backlog priorizado, ciclos registrados e responsáveis definidos |
| PI-03 | Gestão Ágil | Registrar riscos, decisões e evidências | Concluído | Média | Gerente de Projetos | Riscos, decisões e links de evidência disponíveis |
| PI-04 | Gestão Ágil | Manter o Trello e realizar revisão final | Em andamento | Alta | Gerente de Projetos | Cartões atualizados e revisão da entrega registrada |
| PI-05 | IHC | Definir identidade visual e padrões de interface | Concluído | Alta | Desenvolvedor | Marca, paleta, tipografia e regras de aplicação documentadas |
| PI-06 | IHC | Documentar personas e jornadas principais | Concluído | Alta | Arquiteto de Sistemas | Personas e jornadas de proprietário e locatário documentadas |
| PI-07 | IHC | Aplicar avaliação heurística e corrigir interface | Concluído | Alta | Desenvolvedor | Problemas prioritários corrigidos e evidências registradas |
| PI-08 | IHC | Realizar teste com usuários e consolidar resultados | Planejado | Alta | Gerente de Projetos | Sessões realizadas, achados priorizados e relatório concluído |
| PI-09 | Desenvolvimento Web III | Implementar autenticação e autorização com Spring Security | Concluído | Alta | Líder Técnico | Rotas protegidas, senhas codificadas e perfis validados |
| PI-10 | Desenvolvimento Web III | Aplicar validações e tratamento de erros | Concluído | Alta | Desenvolvedor | Entradas inválidas rejeitadas com mensagens compreensíveis |
| PI-11 | Desenvolvimento Web III | Garantir isolamento dos dados por imobiliária | Concluído | Alta | Arquiteto de Sistemas | Usuários acessam somente dados da própria organização |
| PI-12 | Desenvolvimento Web III | Cobrir fluxos críticos com testes automatizados | Em andamento | Alta | Desenvolvedor | Testes de segurança e regras principais executam sem falhas |
| PI-13 | Banco de Dados Não Relacional | Modelar logs de atividades no MongoDB | Concluído | Alta | DBA | Documento de log contém ator, ação, entidade, data e contexto |
| PI-14 | Banco de Dados Não Relacional | Integrar gravação e consulta de auditoria | Concluído | Alta | DBA | Ações relevantes geram logs e a aplicação permite consultá-los |
| PI-15 | Banco de Dados Não Relacional | Definir retenção, índices e proteção dos logs | Em andamento | Média | DBA | Política de retenção e índices documentados e aplicados |
| PI-16 | Produto integrado | Completar o ciclo de vida dos contratos | Planejado | Alta | Líder Técnico | Contratos permitem criação, vigência, reajuste, encerramento e histórico |
| PI-17 | Produto integrado | Cadastrar funcionários e definir perfis de acesso | Planejado | Alta | Desenvolvedor | Administrador cadastra funcionários por CPF e atribui perfis autorizados |
| PI-18 | Produto integrado | Cadastrar proprietários e imóveis de terceiros | Planejado | Alta | Desenvolvedor | Imóvel pode ser vinculado ao proprietário correto, inclusive quando administrado por terceiros |
| PI-19 | Produto integrado | Vincular imóvel e locatário somente pelo contrato | Planejado | Alta | Arquiteto de Sistemas | Cadastro do imóvel não escolhe locatário e o contrato usa apenas imóveis disponíveis |
| PI-20 | Produto integrado | Implementar cobrança e emissão de boleto de aluguel | Planejado | Alta | Líder Técnico | Contrato gera cobrança com vencimento, valor e situação de pagamento |
| PI-21 | Desenvolvimento Web III | Validar cadastros de pessoa física e jurídica | Planejado | Alta | Desenvolvedor | CPF, CNPJ e campos condicionais são validados no frontend e no backend |
| PI-22 | Produto integrado | Criar painel de vencimentos e pendências | Planejado | Média | Desenvolvedor | Painel apresenta contratos, cobranças e atividades que exigem atenção |
| PI-23 | Banco de Dados Não Relacional | Criar consulta administrativa dos logs | Planejado | Média | DBA | Usuário autorizado filtra logs por período, usuário, ação e entidade |
| PI-24 | Infraestrutura | Planejar substituição do Railway | Planejado | Média | Arquiteto de Sistemas | Alternativa de hospedagem, migração, variáveis e retorno documentados |
| PI-25 | Qualidade e entrega | Executar homologação final e consolidar documentação | Planejado | Alta | Gerente de Projetos | Fluxos críticos homologados e documentação final reúne as quatro disciplinas |

## Convenção para cadastro

- Listas sugeridas: `Planejado`, `Em andamento`, `Em validação` e `Concluído`.
- Etiquetas sugeridas: uma por disciplina e uma para `Produto integrado`.
- Cada cartão deve manter o ID no início do título e receber o critério de aceite na descrição.
- Evidências devem ser anexadas por link para o repositório, publicação ou documento correspondente.
