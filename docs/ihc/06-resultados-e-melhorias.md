# Resultados e plano de melhorias de usabilidade

## Melhorias já aplicadas durante o MVP

| Melhoria | Problema tratado | Evidência |
|---|---|---|
| Menu movido para a lateral | Navegação superior pouco escalável | Commit `d90647a` |
| Breadcrumbs nas telas internas | Dificuldade para reconhecer a localização | Componente `Breadcrumb.jsx` |
| Validação por campo | Erros descobertos apenas no backend | Bean Validation e mensagens da API |
| Cadastro público independente | Dependência da conta de demonstração | Commit `02237dc` |
| CPF ou CNPJ no cadastro | Restrição indevida a empresas | `CadastroRequest` e `CadastroPage` |
| Feedback de carregamento | Incerteza durante requisições | Spinners e estados de carregamento |
| Agrupamento de campos opcionais do imóvel | Formulário visualmente longo | Seção recolhível de dados adicionais |
| Auditoria de atividades | Falta de rastreabilidade | Commit `0ea6c32` |

## Melhorias priorizadas após a avaliação

| Prioridade | Melhoria | Responsável sugerido | Situação |
|---|---|---|---|
| Alta | Cadastro reutilizável de locatários | Desenvolvedor | Backlog WEB-15 |
| Alta | Vínculo de locatário apenas no contrato | Arquiteto e desenvolvedor | Backlog WEB-16 |
| Alta | Bloquear mais de um contrato ativo por imóvel | Desenvolvedor | Backlog WEB-17 |
| Alta | Padronizar locador, proprietário e locatário | Líder técnico | Planejado |
| Média | Cadastro de proprietários e imóveis de terceiros | Líder técnico | Backlog WEB-18 |
| Média | Etapa de revisão do contrato | Desenvolvedor | Planejado |
| Média | Menu móvel com rótulos acessíveis | Desenvolvedor | Planejado |
| Média | Identificar boletos como demonstrativos | Gerente de projetos | Planejado |
| Média | Filtros avançados na auditoria | Desenvolvedor e DBA | Backlog BDN-13 |
| Baixa | Glossário fiscal contextual | Arquiteto de sistemas | Planejado |

## Validação pendente com participantes

O plano de testes está pronto, mas os resultados humanos somente poderão ser preenchidos após a
execução com participantes reais. A entrega final deve anexar a tabela de resultados, o SUS e as
alterações aplicadas a partir das observações. Até essa etapa, a evidência disponível é a inspeção
especializada e a avaliação cruzada de projetos realizada em 19 de outubro de 2026.

## Decisão de produto

As alterações estruturais de locatários e proprietários serão desenvolvidas em incrementos futuros
para preservar a estabilidade da versão apresentada. Essa decisão mantém o MVP funcional e registra
as limitações de forma transparente no backlog.
