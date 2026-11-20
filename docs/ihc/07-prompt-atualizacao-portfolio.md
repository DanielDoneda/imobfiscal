# Prompt para atualizar o portfólio do Projeto Integrador

Atualize a página `projeto-integrador.html` do Portfólio DSM preservando o design, as classes CSS e a
estrutura responsiva existentes. Não remova seções nem links válidos. Faça as seguintes alterações:

1. Na seção IHC, mantenha Carlos Mendes como persona principal e acrescente Mariana Oliveira como
   persona secundária: 34 anos, gestora administrativa de pequena imobiliária, experiência digital
   alta, objetivos de rapidez, reutilização de cadastros, prevenção de duplicidade e histórico.
2. Atualize a jornada para incluir: criação da conta, login, dashboard, proprietário, imóvel,
   locatário, contrato, simulação, cobrança e auditoria. Identifique contrato como maior tensão.
3. Informe que a versão publicada é o protótipo navegável de alta fidelidade.
4. Acrescente uma subseção “Avaliação inicial de usabilidade” informando que em 20/11/2026 foi feita
   uma inspeção especializada das telas principais. Não apresente essa inspeção como teste com
   usuários.
5. Liste como pontos positivos: menu lateral, breadcrumbs, campos obrigatórios, feedback de
   carregamento, agrupamento de formulários, tabelas responsivas e prévia fiscal.
6. Liste como problemas priorizados: confusão locador/locatário, redigitação do locatário, ausência de
   bloqueio para imóvel ocupado, contrato longo, menu móvel sem rótulos e boleto acadêmico ambíguo.
7. Informe que o plano de teste com 5 a 8 participantes está preparado, mas ainda precisa ser
   executado. As métricas são sucesso, tempo, erros, pedidos de ajuda, facilidade e SUS.
8. Corrija a arquitetura: produção usa PostgreSQL no Neon, não MySQL/MariaDB.
9. Atualize Banco de Dados Não Relacional: auditoria com MongoDB Atlas está implementada, não apenas
   planejada. Cite o commit `0ea6c32`.
10. Atualize os testes para 16 testes aprovados, sem falhas.
11. Remova a limitação que afirma que o cadastro público usa imobiliária padrão. O cadastro agora
    cria uma nova conta/tenant e aceita CPF ou CNPJ. Cite o commit `02237dc`.
12. Atualize a situação da trilha de auditoria para “implementada”.
13. Acrescente ao roadmap: cadastro de locatários, vínculo somente pelo contrato, bloqueio de
    contratos simultâneos, proprietários PF/PJ e imóveis de terceiros.
14. Inclua links para os documentos no GitHub:
    - `docs/ihc/01-personas.md`
    - `docs/ihc/02-jornada-usuario.md`
    - `docs/ihc/03-fluxo-navegacao-prototipo.md`
    - `docs/ihc/04-plano-teste-usabilidade.md`
    - `docs/ihc/05-avaliacao-especialista.md`
    - `docs/ihc/06-resultados-e-melhorias.md`
    - `docs/entrega/capitulo-2-ihc.md`
15. Preserve acessibilidade: hierarquia correta de títulos, textos alternativos, foco visível,
    contraste e links com descrição compreensível.

Ao final, apresente um resumo das seções alteradas e confirme que nenhuma avaliação humana ou
funcionalidade planejada foi descrita como concluída.
