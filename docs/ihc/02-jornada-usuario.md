# Jornada do usuário no ImobFiscal

A jornada descreve o fluxo principal do MVP, desde o acesso até o acompanhamento da cobrança. Os
pontos de tensão fundamentam as melhorias de interface e os cartões do backlog.

| Etapa | Ação | Pensamento | Emoção | Dor ou risco | Oportunidade de design |
|---|---|---|---|---|---|
| Criar conta | Informa responsável, proprietário/empresa, CPF/CNPJ, e-mail e senha | “Será que estou criando a conta correta?” | Expectativa | Confundir conta pessoal e organização | Explicar que o primeiro usuário será administrador |
| Entrar | Informa e-mail e senha | “Preciso chegar rapidamente às pendências” | Confiança | Espera causada pela inicialização gratuita | Indicar carregamento e explicar eventual demora inicial |
| Dashboard | Consulta totais e atalhos | “O que exige minha atenção?” | Orientação | Indicadores sem prioridade operacional | Destacar contratos e cobranças pendentes |
| Proprietário | Registra dados fiscais e contato | “Esses dados serão usados nos cálculos?” | Cautela | Termos “locador” e “locatário” confundidos | Mostrar definição contextual e finalidade do cadastro |
| Imóvel | Informa endereço e características | “Esse imóvel pertence à pessoa certa?” | Atenção | Vínculo incorreto e formulário extenso | Agrupar campos e mostrar resumo antes de salvar |
| Locatário | Cadastra previamente o inquilino | “Posso reutilizar esse cadastro?” | Segurança | Versão atual exige redigitação no contrato | Criar cadastro reutilizável de locatários |
| Contrato | Seleciona imóvel, locatário, datas e valor | “O imóvel está livre? As datas estão coerentes?” | Alta tensão | Dupla ocupação, erros de data e digitação repetida | Listar apenas imóveis livres e validar coerência |
| Simulação | Consulta IBS/CBS e valor líquido | “Como o sistema chegou a esse valor?” | Curiosidade | Linguagem fiscal complexa | Exibir memória de cálculo e aviso didático |
| Cobrança | Gera o registro de aluguel | “Foi gerado uma vez ou dupliquei?” | Alívio | Confusão entre simulação e boleto bancário real | Confirmar operação e identificar claramente o escopo acadêmico |
| Acompanhamento | Consulta status e auditoria | “O que aconteceu e quem alterou?” | Controle | Poucos filtros e histórico limitado | Filtros por período, usuário, ação e status |

## Curva emocional

```mermaid
xychart-beta
    title "Curva emocional da jornada"
    x-axis [Conta, Login, Painel, Proprietário, Imóvel, Contrato, Simulação, Cobrança, Histórico]
    y-axis "Confiança" 1 --> 5
    line [3, 4, 4, 3, 3, 2, 3, 5, 4]
```

O contrato é o ponto de maior tensão porque reúne imóvel, locatário, datas, valor e consequência
financeira. A confirmação da cobrança é o maior alívio, desde que o sistema deixe claro que a emissão
atual é demonstrativa.

## Indicadores propostos

- Taxa de conclusão de cada tarefa;
- Tempo para cadastrar imóvel e contrato;
- Quantidade de erros de validação por formulário;
- Número de retornos ou abandonos durante o contrato;
- Escala de facilidade percebida de 1 a 5;
- SUS — System Usability Scale após o teste completo.
