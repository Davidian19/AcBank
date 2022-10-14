package br.com.cesarschool.poo.geral;

import java.util.Scanner;

public class InterfaceAccount {
		
		private static final int NUMERO_DESCONHECIDO = -1;
		private final Scanner ENTRADA = new Scanner(System.in);
		private RepositoryAccount repositoryAccount = new RepositoryAccount();
		
		public void executaTela() {
			while(true) {
				long number = NUMERO_DESCONHECIDO;
				imprimeMenuPrincipal();
				int opcao = ENTRADA.nextInt();
				if (opcao == 1) {
					processaInclusao();
				} else if (opcao == 2) {
					number = processaBusca();
					if (number != NUMERO_DESCONHECIDO) {
						processaAlteracao(number);
					} 
				} else if (opcao == 3) {
					number = processaBusca();
					if (number != NUMERO_DESCONHECIDO) {
						processaEncerrar(number);
					}			
				} else if (opcao == 4) {
					number = processaBusca();
					if (number != NUMERO_DESCONHECIDO) {
						processaBloquear(number);
					}
				} else if (opcao == 5) {
					number = processaBusca();
					if (number != NUMERO_DESCONHECIDO) {
						processaDesbloquear(number);
					}
				} else if (opcao == 6) {
					number = processaBusca();
					if (number != NUMERO_DESCONHECIDO) {
						processaExclusao(number);
					}
				} else if (opcao == 7) {
					processaBusca();
					
				} else if (opcao == 8) {
					number = processaBusca();
					if (number != NUMERO_DESCONHECIDO) {
						processaCreditar(number);
					}
				} else if (opcao == 9) {
					number = processaBusca();
					if (number != NUMERO_DESCONHECIDO) {
						processaDebitar(number);
					}
				} else if(opcao == 10) {
					System.out.println("Encerrando o processo de Cadastro de Accounts!!");
					System.exit(0);
				} else {
					System.out.println("Op��o inv�lida!!");
				}
			} 
		}
		
		private void imprimeMenuPrincipal() {		
			System.out.println("1- Criar Account");
			System.out.println("2- Alterar Account");
			System.out.println("3- Encerrar Account");
			System.out.println("4- Bloquear Account");
			System.out.println("5- Desbloquear Account");
			System.out.println("6- Delete Account");
			System.out.println("7- search Account");
			System.out.println("8- Creditar");
			System.out.println("9- Debitar");
			System.out.println("10- Sair");
			System.out.print("Digite a op��o: ");
		}
		
		private void processaInclusao() {
			Account account = capturaAccount(NUMERO_DESCONHECIDO);
			String retornoValidacao = validate(account);
			if (retornoValidacao == null) {
				boolean retornoRepository = repositoryAccount.include(account);
				if (retornoRepository) {
					System.out.println("Account inclu�da com sucesso!");
				} else {
					System.out.println("Erro na inclus�o de account!");
				}
			} else {
				System.out.println(retornoValidacao);
			}		
		}
		
		private String validate(Account Account) {
			int validacaoAccount = Account.isAccount();
			if (validacaoAccount == 1) {
				return "Erro: Um número inválido foi inserido.";
			} else if (validacaoAccount == 2) {
				return "Erro: Um número inválido foi inserido.";
			} else if (validacaoAccount == 3) {
				return "Erro: Uma Data inválida foi inserida.";
			} else {
				return null;
			}
		}
		
		private Account capturaAccount(long numberDaAlteracao) {
			long numberAccount;
			if (numberDaAlteracao == NUMERO_DESCONHECIDO) {
				System.out.print("Digite o number da account: ");
				numberAccount = ENTRADA.nextLong();			
			} else {
				numberAccount = numberDaAlteracao;
			}
			System.out.print("Digite a data em que sua account foi aberta (YYYY-MM-DD): ");
			String dataAberturaString = ENTRADA.next();
			System.out.print("Digite o tipo da account (1, 2 ou 3): ");
			int codigoStatus = ENTRADA.nextInt();
			return new Account(numberAccount, codigoStatus, dataAberturaString);
		}

		private void processaAlteracao(long number) {
			Account account = repositoryAccount.search(number);
			String retornoValidacao = validate(account);
			if (retornoValidacao == null) {
				System.out.println("Digite a data de abertura para modificar (YYYY-MM-DD): ");
				String dataAberturaModificada = ENTRADA.next();
				boolean retornoRepository = repositoryAccount.change(dataAberturaModificada, account);
				if (retornoRepository) {
					System.out.println("Data de Abertura alterada com sucesso!");
				} else {
					System.out.println("Erro na altera��o da Data de abertura!");
				}
			} else {
				System.out.println(retornoValidacao);
			}		
		}
		
		private long processaBusca() {
			System.out.print("Digite o number da Conta: ");
			long number = ENTRADA.nextLong();
			Account account = repositoryAccount.search(number);
			if (account == null) {
				System.out.println("Account n�o encontrada!");
				return NUMERO_DESCONHECIDO;
			} else {
				System.out.println("Number Da Conta: " + account.getNumber());
				System.out.println("Data de Abertura da Conta: " + account.getDataAbertura());
				System.out.println("Saldo da Conta: " + account.getBalance());
				System.out.println("Status da Conta: " + account.getStatusDescription());
				System.out.println("Score da Conta: " + account.calcularEscore());
				return number;
			}
		}
		
		private void processaExclusao(long number) {
			boolean retornoRepository = repositoryAccount.delete(number);
			if (retornoRepository) {
				System.out.println("Conta Excluida com sucesso.");
			} else {
				System.out.println("Erro ao excluir Conta.");
			}
		}
		
		private void processaEncerrar(long number) {
			Account account = repositoryAccount.search(number);
			String retornoValidacao = validate(account);
			if (retornoValidacao == null) {
				System.out.println("Encerrando conta...");
				boolean retornoRepository = repositoryAccount.encerrar(account);
				if (retornoRepository) {
					System.out.println("Sua conta foi encerrada com sucesso.");
				} else {
					System.out.println("Erro no encerramento da Conta!");
				}
			} else {
				System.out.println(retornoValidacao);
			}		
		}
		
		private void processaBloquear(long number) {
			Account account = repositoryAccount.search(number);
			String retornoValidacao = validate(account);
			if (retornoValidacao == null) {
				System.out.println("Bloqueando conta...");
				boolean retornoRepository = repositoryAccount.bloquear(account);
				if (retornoRepository) {
					System.out.println("Conta bloqueada com sucesso.");
				} else {
					System.out.println("Erro ao bloquear Conta.");
				}
			} else {
				System.out.println(retornoValidacao);
			}		
		}
		
		private void processaDesbloquear(long number) {
			Account account = repositoryAccount.search(number);
			String retornoValidacao = validate(account);
			if (retornoValidacao == null) {
				System.out.println("Desbloqueando Conta...");
				boolean retornoRepository = repositoryAccount.desbloquear(account);
				if (retornoRepository) {
					System.out.println("Conta desbloqueada com sucesso.");
				} else {
					System.out.println("Erro ao desbloquear Conta!");
				}
			} else {
				System.out.println(retornoValidacao);
			}
		}
		
		private void processaCreditar(long number) {
			Account account = repositoryAccount.search(number);
			String retornoValidacao = validate(account);
			if (retornoValidacao == null) {
				System.out.println("Quanto você deseja creditar da Conta? ");
				double ValorCreditado = ENTRADA.nextDouble();
				boolean retornoRepository = repositoryAccount.creditarValor(ValorCreditado, account);
				if (retornoRepository) {
					System.out.println("Valor creditado com sucesso.");
				} else {
					System.out.println("Erro ao creditar.");
				}
			} else {
				System.out.println(retornoValidacao);
			}
		}
		
		private void processaDebitar(long number) {
			Account account = repositoryAccount.search(number);
			String retornoValidacao = validate(account);
			if (retornoValidacao == null) {
				System.out.println("Quanto você deseja Debitar da Conta? ");
				double Valordebitado = ENTRADA.nextDouble();
				boolean retornoRepository = repositoryAccount.debitar(Valordebitado, account);
				if (retornoRepository) {
					System.out.println("Valor debitado com sucesso.");
				} else {
					System.out.println("Erro ao debitar.");
				}
			} else {
				System.out.println(retornoValidacao);
			}
		}
	}
