# Personas do ImobFiscal

As personas representam os dois públicos prioritários do MVP: o proprietário que administra suas
próprias locações e a profissional de uma pequena imobiliária. Elas orientam decisões de linguagem,
navegação, prevenção de erros e priorização do backlog; não representam entrevistas reais.

## Persona principal — Carlos Mendes

| Campo | Descrição |
|---|---|
| Perfil | 46 anos, empresário autônomo e proprietário de imóveis |
| Experiência digital | Intermediária; utiliza planilhas, e-mail e WhatsApp |
| Contexto | Administra diferentes locações e consulta o contador para questões fiscais |
| Objetivo principal | Centralizar imóveis, contratos, cobranças e informações fiscais |
| Comportamento | Confere valores mais de uma vez e evita ferramentas com linguagem excessivamente técnica |
| Frustrações | Dados duplicados, documentos espalhados, esquecimentos e baixa visão das pendências |
| Necessidades | Painel claro, formulários guiados, confirmações e explicação dos cálculos |
| Acessibilidade | Textos legíveis, ações previsíveis e operação confortável em notebook |

> “Eu preciso controlar meus aluguéis sem depender de várias planilhas e sem correr o risco de errar na parte fiscal.”

### Cenário de uso

Carlos acessa o sistema no início da semana para conferir o patrimônio, cadastrar um imóvel, registrar
um contrato e gerar a cobrança do aluguel. Ele espera entender rapidamente o que está pendente e
receber confirmação antes de concluir ações importantes.

## Persona secundária — Mariana Oliveira

| Campo | Descrição |
|---|---|
| Perfil | 34 anos, gestora administrativa de uma pequena imobiliária |
| Experiência digital | Alta em sistemas comerciais e intermediária em conceitos fiscais |
| Contexto | Atende proprietários, organiza contratos e acompanha cobranças durante o expediente |
| Objetivo principal | Executar operações com rapidez sem misturar dados de clientes e imóveis |
| Comportamento | Alterna entre muitos registros, pesquisa por documento e trabalha com prazos |
| Frustrações | Digitação repetitiva, ausência de filtros, cadastros duplicados e permissões imprecisas |
| Necessidades | Pesquisa, reutilização de cadastros, status visíveis, histórico e perfis de acesso |
| Acessibilidade | Navegação por teclado, mensagens objetivas e alvos de clique adequados |

> “Preciso encontrar o cadastro certo e concluir o contrato sem redigitar dados ou correr o risco de usar o imóvel errado.”

### Cenário de uso

Mariana cadastra locatários, seleciona imóveis disponíveis e acompanha contratos e cobranças. Para
ela, a produtividade depende de cadastros reutilizáveis, filtros e regras que impeçam vínculos
inconsistentes.

## Implicações para o design

- Usar “proprietário/locador” e “inquilino/locatário” de forma consistente;
- Exibir ações principais no contexto da tarefa;
- Dividir formulários longos em grupos compreensíveis;
- Reaproveitar cadastros para reduzir digitação e inconsistências;
- Informar carregamento, sucesso, erro e consequência de ações;
- Impedir contratos simultâneos para o mesmo imóvel;
- Preservar contraste, foco visível e áreas clicáveis de pelo menos 44 px;
- Manter histórico e isolamento de dados entre contas.
