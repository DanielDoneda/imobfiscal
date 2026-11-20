# Backlog — Interação Humano-Computador

Este backlog organiza os cartões da disciplina de Interação Humano-Computador para reprodução no
Trello. Os itens distinguem entregas já realizadas, validações em andamento e melhorias futuras,
preservando a transparência entre evidência real e trabalho planejado.

## Concluído

### IHC-01 — Definir a persona principal
- **Responsável:** Gerente de projetos
- **Informado por:** Líder técnico
- **Prioridade:** Alta
- **Estimativa:** 3 pontos
- **Descrição:** Representar o proprietário que administra locações com apoio de planilhas e canais
  dispersos.
- **Aceite:** persona contém perfil, contexto, objetivos, frustrações, necessidades, comportamento e
  cenário de uso.
- **Evidência:** `docs/ihc/01-personas.md` e portfólio do PI.

### IHC-02 — Criar a persona secundária
- **Responsável:** Gerente de projetos
- **Informado por:** Arquiteto de sistemas
- **Prioridade:** Média
- **Estimativa:** 3 pontos
- **Descrição:** Representar uma gestora administrativa de pequena imobiliária.
- **Aceite:** persona contempla produtividade, reutilização de cadastros, pesquisa, prevenção de
  duplicidade e necessidade de histórico.
- **Evidência:** persona Mariana Oliveira em `docs/ihc/01-personas.md`.

### IHC-03 — Mapear a jornada principal do usuário
- **Responsável:** Arquiteto de sistemas
- **Informado por:** Gerente de projetos
- **Prioridade:** Alta
- **Estimativa:** 5 pontos
- **Descrição:** Mapear da criação da conta ao acompanhamento da auditoria.
- **Aceite:** jornada contém ação, pensamento, emoção, dor, risco e oportunidade de design.
- **Evidência:** `docs/ihc/02-jornada-usuario.md`.

### IHC-04 — Identificar os pontos de tensão da jornada
- **Responsável:** Líder técnico
- **Informado por:** Arquiteto de sistemas
- **Prioridade:** Alta
- **Estimativa:** 2 pontos
- **Descrição:** Identificar etapas com maior possibilidade de erro ou abandono.
- **Aceite:** contrato é documentado como maior tensão e confirmação da cobrança como maior alívio.
- **Evidência:** curva emocional e jornada documentadas.

### IHC-05 — Documentar o fluxo de navegação
- **Responsável:** Arquiteto de sistemas
- **Informado por:** Desenvolvedor
- **Prioridade:** Alta
- **Estimativa:** 5 pontos
- **Descrição:** Representar visualmente as rotas e relações entre os módulos.
- **Aceite:** fluxo inclui autenticação, dashboard, proprietários, imóveis, contratos, simulação,
  cobranças e auditoria.
- **Evidência:** diagrama Mermaid em `docs/ihc/03-fluxo-navegacao-prototipo.md`.

### IHC-06 — Consolidar o protótipo navegável
- **Responsável:** Desenvolvedor
- **Informado por:** Líder técnico
- **Prioridade:** Alta
- **Estimativa:** 8 pontos
- **Descrição:** Disponibilizar uma versão funcional de alta fidelidade para avaliação.
- **Aceite:** principais módulos podem ser percorridos em ambiente publicado.
- **Evidência:** `https://imobfiscal-sigma.vercel.app/`.

### IHC-07 — Mover a navegação para o menu lateral
- **Responsável:** Desenvolvedor
- **Informado por:** Usuário avaliador
- **Prioridade:** Média
- **Estimativa:** 3 pontos
- **Descrição:** Substituir o menu superior por uma navegação lateral persistente.
- **Aceite:** item atual é destacado; módulos permanecem acessíveis; usuário e saída aparecem no
  rodapé do menu.
- **Evidência:** commit `d90647a` e `Layout.jsx`.

### IHC-08 — Aplicar feedback e prevenção básica de erros
- **Responsável:** Desenvolvedor
- **Informado por:** Arquiteto de sistemas
- **Prioridade:** Alta
- **Estimativa:** 5 pontos
- **Descrição:** Indicar campos obrigatórios, carregamento, sucesso, erro e confirmação de ações.
- **Aceite:** formulários usam validação; requisições mostram carregamento; erros da API são exibidos
  em linguagem compreensível.
- **Evidência:** páginas React, Bean Validation e componentes de alerta.

### IHC-09 — Realizar inspeção especializada de usabilidade
- **Responsável:** Arquiteto de sistemas
- **Informado por:** Gerente de projetos
- **Prioridade:** Alta
- **Estimativa:** 5 pontos
- **Descrição:** Percorrer as principais telas aplicando princípios heurísticos de usabilidade.
- **Aceite:** pontos positivos e problemas possuem heurística, severidade e recomendação.
- **Evidência:** `docs/ihc/05-avaliacao-especialista.md`, realizada em 20/11/2026.

### IHC-10 — Produzir o capítulo acadêmico de IHC
- **Responsável:** Gerente de projetos
- **Informado por:** Líder técnico
- **Prioridade:** Alta
- **Estimativa:** 5 pontos
- **Descrição:** Consolidar personas, jornada, protótipo, avaliação e melhorias para o documento final.
- **Aceite:** capítulo diferencia inspeção especializada de teste com participantes.
- **Evidência:** `docs/entrega/capitulo-2-ihc.md`.

### IHC-11 — Atualizar o portfólio do Projeto Integrador
- **Responsável:** Líder técnico
- **Informado por:** Gerente de projetos
- **Prioridade:** Média
- **Estimativa:** 3 pontos
- **Descrição:** Publicar as evidências atuais de IHC e corrigir informações técnicas antigas.
- **Aceite:** portfólio apresenta duas personas, jornada ampliada, avaliação inicial, plano de teste e
  links para as evidências.
- **Evidência:** commit `435f290` do repositório `dsmfatec`.

## Em andamento

### IHC-12 — Executar testes com participantes reais
- **Responsável:** Gerente de projetos
- **Informado por:** Arquiteto de sistemas
- **Prioridade:** Alta
- **Estimativa:** 8 pontos
- **Descrição:** Aplicar o roteiro com 5 a 8 participantes representativos.
- **Aceite:** consentimento respeitado; dados fictícios utilizados; sete tarefas aplicadas; tempo,
  erros, ajuda e comentários registrados.
- **Evidência esperada:** fichas preenchidas conforme `docs/ihc/04-plano-teste-usabilidade.md`.

### IHC-13 — Calcular indicadores e SUS
- **Responsável:** Gerente de projetos
- **Informado por:** Líder técnico
- **Prioridade:** Alta
- **Estimativa:** 3 pontos
- **Descrição:** Consolidar os resultados quantitativos e qualitativos das sessões.
- **Aceite:** taxa de sucesso, tempo mediano, erros, facilidade percebida e SUS são calculados sem
  inventar ou alterar respostas.
- **Dependência:** IHC-12.

### IHC-14 — Priorizar melhorias após os testes
- **Responsável:** Líder técnico
- **Informado por:** Gerente de projetos
- **Prioridade:** Alta
- **Estimativa:** 3 pontos
- **Descrição:** Converter problemas observados em cartões com severidade, responsável e aceite.
- **Aceite:** problemas críticos são priorizados; decisões e itens adiados possuem justificativa.
- **Dependências:** IHC-12 e IHC-13.

## Backlog futuro

### IHC-15 — Padronizar locador, proprietário e locatário
- **Responsável:** Líder técnico
- **Informado por:** Usuário avaliador
- **Prioridade:** Alta
- **Estimativa:** 3 pontos
- **Descrição:** Revisar títulos, campos, mensagens e textos de ajuda para reduzir ambiguidade.
- **Aceite:** locador é identificado como proprietário e locatário como inquilino em todo o sistema.

### IHC-16 — Criar cadastro reutilizável de locatários
- **Responsável:** Desenvolvedor
- **Informado por:** Arquiteto de sistemas
- **Prioridade:** Alta
- **Estimativa:** 8 pontos
- **Descrição:** Evitar redigitação dos dados do inquilino no contrato.
- **Aceite:** contrato seleciona locatário previamente cadastrado e pertencente ao tenant.
- **Relação:** WEB-15 e WEB-16.

### IHC-17 — Mostrar somente imóveis disponíveis no contrato
- **Responsável:** Desenvolvedor
- **Informado por:** Arquiteto de sistemas
- **Prioridade:** Alta
- **Estimativa:** 5 pontos
- **Descrição:** Prevenir a seleção de imóvel que já possui contrato ativo.
- **Aceite:** imóvel ocupado não aparece na seleção; mensagem explica quando não há disponibilidade.
- **Relação:** WEB-17.

### IHC-18 — Criar etapa de revisão do contrato
- **Responsável:** Desenvolvedor
- **Informado por:** Usuário avaliador
- **Prioridade:** Média
- **Estimativa:** 5 pontos
- **Descrição:** Exibir um resumo antes da confirmação do contrato.
- **Aceite:** usuário confere imóvel, locatário, datas, valor e vencimento antes de salvar.

### IHC-19 — Melhorar a navegação em dispositivos móveis
- **Responsável:** Desenvolvedor
- **Informado por:** Arquiteto de sistemas
- **Prioridade:** Média
- **Estimativa:** 5 pontos
- **Descrição:** Evitar que a navegação móvel dependa exclusivamente da interpretação de ícones.
- **Aceite:** menu recolhível mostra rótulos; foco e leitura por tecnologia assistiva são preservados.

### IHC-20 — Identificar cobranças como demonstrativas
- **Responsável:** Gerente de projetos
- **Informado por:** Arquiteto de sistemas
- **Prioridade:** Alta
- **Estimativa:** 3 pontos
- **Descrição:** Evitar que o usuário interprete o boleto acadêmico como emissão bancária real.
- **Aceite:** título, confirmação e detalhe informam claramente “cobrança demonstrativa”.

### IHC-21 — Substituir confirmações nativas por modais consistentes
- **Responsável:** Desenvolvedor
- **Informado por:** Líder técnico
- **Prioridade:** Baixa
- **Estimativa:** 3 pontos
- **Descrição:** Padronizar exclusões e ações destrutivas com contexto e consequência.
- **Aceite:** modal exibe o registro afetado, oferece cancelar como alternativa segura e mantém foco
  acessível.

### IHC-22 — Criar filtros e prioridades no dashboard e auditoria
- **Responsável:** Desenvolvedor
- **Informado por:** Gerente de projetos
- **Prioridade:** Média
- **Estimativa:** 8 pontos
- **Descrição:** Aumentar a visibilidade de pendências e a eficiência de usuários frequentes.
- **Aceite:** dashboard destaca vencimentos; auditoria filtra por período, usuário, ação e resultado.

## Critério de conclusão da disciplina

A documentação acadêmica, as personas, a jornada, o protótipo e a avaliação especializada estão
concluídos. A validação empírica será finalizada após a execução do roteiro com participantes reais,
o cálculo dos indicadores e o registro das iterações decorrentes dos resultados.
