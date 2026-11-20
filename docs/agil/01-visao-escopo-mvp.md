# Visão e escopo ágil do ImobFiscal

## Visão do produto

Para proprietários e pequenas imobiliárias que controlam imóveis, contratos e cobranças em fontes
dispersas, o ImobFiscal é uma plataforma web de apoio à gestão imobiliária e fiscal. O produto
centraliza informações, protege os dados de cada conta e oferece uma simulação acadêmica de IBS/CBS.

## Problema

Planilhas, documentos e mensagens fragmentam o trabalho, dificultam a identificação de pendências e
aumentam a possibilidade de duplicidade, esquecimento e erro. A linguagem tributária também eleva a
carga cognitiva de pessoas que não são especialistas fiscais.

## Público prioritário

- Proprietários que administram os próprios imóveis;
- Pequenas imobiliárias e seus profissionais administrativos;
- Contadores como interessados indiretos nas informações organizadas.

## Objetivo do MVP

Validar um fluxo web no qual o usuário cria sua conta, entra com segurança, registra informações do
patrimônio, cria contratos, simula tributos, gera cobranças demonstrativas e consulta atividades.

## Escopo entregue

- Cadastro de conta PF/PJ e primeiro administrador;
- Autenticação com Spring Security, JWT e BCrypt;
- Isolamento multi-tenant;
- Cadastros de locadores e imóveis;
- Contratos com dados do locatário;
- Simulador acadêmico de IBS/CBS;
- Cobranças demonstrativas;
- Auditoria no MongoDB Atlas;
- Frontend na Vercel, backend no Render e PostgreSQL no Neon;
- 16 testes automatizados aprovados.

## Fora do escopo atual

- Emissão fiscal oficial;
- Boleto registrado ou integração bancária;
- Split payment real;
- Assinatura eletrônica;
- Aplicativo móvel nativo;
- Cadastro reutilizável de locatários;
- Gestão completa de funcionários e permissões;
- Recuperação de senha;
- Administração formal de imóveis de terceiros.

## Critérios de sucesso

- Jornada principal demonstrável em ambiente online;
- Dados de uma conta inacessíveis por outra conta;
- Validações e erros compreensíveis;
- Build de produção aprovado;
- Testes automatizados sem falhas;
- Backlog rastreável com evidências e trabalho futuro explícito.

## Restrições e premissas

- Projeto acadêmico com equipe multidisciplinar;
- Uso de serviços gratuitos sujeitos a pausa por inatividade;
- Cálculo fiscal exclusivamente didático;
- Evolução incremental sem interromper o MVP publicado;
- Dados de demonstração devem ser fictícios.
