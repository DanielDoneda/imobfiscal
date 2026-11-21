# Integração Python — relatórios gerenciais

## Objetivo acadêmico

Aplicar Python ao ImobFiscal em uma responsabilidade compatível com seus pontos fortes: análise
de dados e geração de relatórios. O módulo não substitui a API Java nem duplica as regras do
domínio.

## Arquitetura

```text
React -> Spring Boot / Spring Security -> PostgreSQL
                    |
                    | JWT e endpoints protegidos
                    v
           FastAPI / análise Python
                    |
                    v
             JSON ou arquivo CSV
```

O usuário autentica no backend Java. Ao solicitar um relatório, o cliente envia o mesmo JWT ao
FastAPI. O serviço Python consulta imóveis, locadores, contratos e boletos no Java. O filtro JWT
do Spring compara o tenant da URL com a imobiliária assinada no token.

## Conteúdos de Python demonstrados

- criação de API REST com FastAPI;
- programação assíncrona nas consultas com `asyncio` e `httpx`;
- funções puras para análise dos dados;
- manipulação de datas e valores monetários com `date` e `Decimal`;
- estruturas de dados, filtros, conjuntos e agregações;
- exportação CSV;
- testes automatizados com `unittest`;
- configuração por variáveis de ambiente e execução em Docker.

## Indicadores calculados

- taxa de ocupação dos imóveis;
- receita mensal prevista pelos contratos ativos;
- contratos com vencimento em até 30 dias;
- boletos pagos, pendentes e vencidos;
- valores pendentes e vencidos.

## Limites

Os indicadores refletem os dados e status fornecidos pela API Java. O serviço não altera
contratos, boletos ou imóveis. PDF, Excel, gráficos e uma tela específica no frontend podem ser
adicionados posteriormente sem mudar o núcleo do cálculo.
