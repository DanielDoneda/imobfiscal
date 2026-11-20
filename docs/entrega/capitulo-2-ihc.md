# Interação Humano-Computador

## Objetivo

A aplicação dos conceitos de Interação Humano-Computador no ImobFiscal teve como objetivo reduzir a
complexidade da gestão de locações e tornar as ações do sistema compreensíveis, previsíveis e
seguras. O trabalho foi orientado por personas, jornada, fluxo de navegação, protótipo funcional e
avaliação inicial de usabilidade.

## Público e personas

A persona principal é Carlos Mendes, proprietário de 46 anos com domínio digital intermediário que
administra imóveis por planilhas e mensagens. Ele busca centralização, visibilidade e segurança nos
cálculos. A persona secundária é Mariana Oliveira, gestora administrativa de 34 anos que precisa
executar cadastros e contratos com rapidez, reutilizar dados e evitar mistura entre clientes.

As personas indicaram a necessidade de linguagem clara, prevenção de erros, formulários agrupados,
feedback imediato, pesquisa e histórico de alterações.

## Jornada

A jornada compreende criação da conta, login, dashboard, cadastro de proprietário, imóvel, contrato,
simulação, cobrança e acompanhamento. O contrato foi identificado como o ponto de maior tensão por
reunir imóvel, locatário, datas, valores e efeito financeiro. A confirmação da cobrança representa o
maior alívio, desde que o caráter demonstrativo seja informado.

## Protótipo e navegação

A versão publicada do ImobFiscal foi utilizada como protótipo navegável de alta fidelidade. O menu
lateral dá acesso a Dashboard, Imóveis, Locadores, Contratos, Simulador, Boletos e Auditoria. A
interface utiliza breadcrumbs, ações primárias próximas ao título, agrupamento de campos, validação,
feedback de carregamento e tabelas responsivas.

## Avaliação inicial

Em 20 de novembro de 2026 foi realizada uma inspeção especializada das principais telas. Foram
observados como pontos positivos a consistência do menu, orientação por breadcrumbs, identificação de
campos obrigatórios, feedback de carregamento, agrupamento dos formulários e prévia fiscal.

Os principais problemas foram a possível confusão entre locador e locatário, redigitação do inquilino
no contrato, ausência de bloqueio visível para imóveis ocupados, formulário contratual longo, menu
móvel dependente de ícones e possível interpretação do boleto acadêmico como emissão bancária real.

## Teste com participantes

Foi preparado um plano para 5 a 8 participantes, com sete tarefas representativas: criar conta,
entrar, interpretar o dashboard, cadastrar proprietário, cadastrar imóvel, criar contrato, gerar
cobrança e consultar auditoria. Serão medidos sucesso, tempo, erros, ajuda, facilidade percebida e
SUS. A meta é pelo menos 80% de conclusão sem ajuda, nenhum problema crítico e SUS igual ou superior
a 68.

O plano não foi apresentado como resultado humano: sua execução com participantes permanece como
atividade necessária para a validação final.

## Melhorias e iterações

Durante o MVP foram aplicados menu lateral, breadcrumbs, validação por campo, feedback de
carregamento, cadastro público independente, suporte a CPF/CNPJ e auditoria. A avaliação também gerou
melhorias futuras: cadastro reutilizável de locatários, vínculo exclusivamente pelo contrato,
bloqueio de dupla ocupação, cadastro formal de proprietários, revisão do contrato, menu móvel com
rótulos e filtros de auditoria.

## Evidências

- `docs/ihc/01-personas.md`;
- `docs/ihc/02-jornada-usuario.md`;
- `docs/ihc/03-fluxo-navegacao-prototipo.md`;
- `docs/ihc/04-plano-teste-usabilidade.md`;
- `docs/ihc/05-avaliacao-especialista.md`;
- `docs/ihc/06-resultados-e-melhorias.md`;
- Aplicação publicada em `https://imobfiscal-sigma.vercel.app/`.

## Conclusão

O ImobFiscal possui base consistente de IHC e protótipo navegável. A documentação cobre personas,
jornada, fluxo e validação inicial. A conclusão empírica da disciplina depende agora da aplicação do
roteiro com participantes reais, registro das métricas e comparação dos resultados após as melhorias.
