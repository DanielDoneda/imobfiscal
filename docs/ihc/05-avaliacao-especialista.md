# Avaliação especializada de usabilidade do ImobFiscal

## Método e escopo

Em 20 de novembro de 2026 foi realizada uma inspeção especializada da versão publicada do
ImobFiscal. Foram percorridas as telas Dashboard, Imóveis, Novo Imóvel, Locadores, Contratos, Novo
Contrato, Simulador, Boletos e Auditoria. A análise utilizou princípios heurísticos de visibilidade do
estado, correspondência com o mundo real, consistência, prevenção de erros, reconhecimento e
acessibilidade básica.

Esta inspeção é uma validação inicial e não substitui sessões com participantes reais.

## Pontos positivos observados

- Menu lateral consistente e com item ativo;
- Breadcrumbs facilitam a orientação;
- Ações principais possuem destaque visual;
- Formulários identificam campos obrigatórios;
- Endereço pode ser auxiliado pelo CEP;
- Carregamento e erros possuem feedback;
- Formulários são agrupados por assunto;
- Tabelas utilizam contêiner responsivo;
- Auditoria apresenta usuário, ação, recurso, resultado e data;
- Contrato oferece prévia fiscal junto ao preenchimento.

## Problemas identificados

| ID | Problema | Heurística | Severidade | Recomendação |
|---|---|---|---:|---|
| IHC-01 | Locador, proprietário e locatário podem ser confundidos | Mundo real e consistência | 3 | Padronizar termos e incluir explicação contextual |
| IHC-02 | Locatário é redigitado no contrato e não possui cadastro reutilizável | Eficiência e prevenção de erros | 3 | Criar cadastro de locatários e seleção no contrato |
| IHC-03 | A lista do contrato não evidencia se o imóvel está livre | Prevenção de erros | 4 | Exibir somente imóveis disponíveis e validar no backend |
| IHC-04 | Formulário do contrato concentra muitos campos sem revisão final | Controle e prevenção de erros | 3 | Dividir em seções/etapas e mostrar resumo antes de salvar |
| IHC-05 | Menu móvel oculta todos os rótulos e depende dos ícones | Reconhecimento | 3 | Criar menu recolhível com rótulos ou tooltips acessíveis |
| IHC-06 | Cadastro de imóvel permite criar proprietário dentro do formulário | Consistência e carga cognitiva | 2 | Separar cadastros e manter atalho claramente identificado |
| IHC-07 | “Boleto” pode ser interpretado como cobrança bancária real | Correspondência com o mundo real | 3 | Usar “cobrança demonstrativa” e aviso permanente |
| IHC-08 | Simulação fiscal usa termos técnicos sem ajuda em todos os campos | Ajuda e documentação | 2 | Adicionar textos curtos e glossário contextual |
| IHC-09 | Algumas exclusões usam confirmação nativa do navegador | Consistência | 2 | Utilizar modal com nome do registro e consequência |
| IHC-10 | Não há recuperação de senha | Recuperação de erros | 3 | Criar fluxo seguro de redefinição |
| IHC-11 | Página de auditoria oferece pouca filtragem | Flexibilidade e eficiência | 2 | Filtrar por período, usuário, ação e resultado |
| IHC-12 | Dashboard não prioriza claramente pendências | Visibilidade do estado | 2 | Destacar contratos a vencer e cobranças pendentes |

## Síntese

O sistema possui uma estrutura coerente e suficiente para demonstração do MVP. Os riscos de maior
impacto concentram-se na criação do contrato: terminologia, digitação repetitiva e ausência de uma
regra visível de disponibilidade do imóvel. Esses pontos foram transformados em trabalho futuro no
backlog de Desenvolvimento Web III.
