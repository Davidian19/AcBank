package br.com.cesarschool.poo.telas;

import br.com.cesarschool.poo.entidades.Correntista;
import br.com.cesarschool.poo.entidades.poupancaAccount;
import br.com.cesarschool.poo.repositorios.CorrentistRepository;
import br.com.cesarschool.poo.repositorios.RepositoryAccount;
import br.com.cesarschool.poo.entidades.Account;
import br.com.cesarschool.poo.mediators.CorrentistaMediators;
import br.com.cesarschool.poo.mediators.MediatorsAcount;


import java.util.Scanner;

public class InterfaceAccount {
		
		private static final int NUMERO_DESCONHECIDO = -1;
		private final Scanner ENTRADA = new Scanner(System.in);
		private RepositoryAccount repositoryAccount = new RepositoryAccount();
		private MediatorsAcount mediator = new MediatorsAcount();
		
		
		public void executaTela() {
			while(true) {
				long number = NUMERO_DESCONHECIDO;
				printMenu();
				int option = ENTRADA.nextInt();
				if (option == 1) {
					processaInclusao();
				} else if (option == 2) {
					number = searchProcessing();
					if (number != NUMERO_DESCONHECIDO) {
						changeProcessing(number);
					} 
				} else if (option == 3) {
					number = searchProcessing();
					if (number != NUMERO_DESCONHECIDO) {
						closesProcessing(number);
					}			
				} else if (option == 4) {
					number = searchProcessing();
					if (number != NUMERO_DESCONHECIDO) {
						blockProcessing(number);
					}
				} else if (option == 5) {
					number = searchProcessing();
					if (number != NUMERO_DESCONHECIDO) {
						unblockProcessing(number);
					}
				} else if (option == 6) {
					number = searchProcessing();
					if (number != NUMERO_DESCONHECIDO) {
						deleteProcessing(number);
					}
				} else if (option == 7) {
					searchProcessing();
					
				} else if (option == 8) {
					number = searchProcessing();
					if (number != NUMERO_DESCONHECIDO) {
						creditorProcessing(number);
					}
				} else if (option == 9) {
					number = searchProcessing();
					if (number != NUMERO_DESCONHECIDO) {
						debtorProcessing(number);
					}
				} else if(option == 10) {
					System.out.println("--------------------------------------------");
					System.out.println("Encerrando o processo de Cadastro de contas.");
					System.out.println("--------------------------------------------");
					System.exit(0);
				} else {
					System.out.println("Error: Opção inválida.");
				}
			} 
		}
		
		private void printMenu() {		
			System.out.println("=======================================");
			System.out.println("1- Adicionar conta");
			System.out.println("2- Alterar conta");
			System.out.println("3- Encerrar conta");
			System.out.println("4- Bloquear conta");
			System.out.println("5- Desbloquear conta");
			System.out.println("6- Excluir conta");
			System.out.println("7- Buscar conta");
			System.out.println("8- Creditar");
			System.out.println("9- Debitar");
			System.out.println("10- Sair");
			System.out.println("---------------------------------------");
			System.out.print("Digite a opção: ");
		}
		
		private void processaInclusao() {
			Account account = capturaAccount(NUMERO_DESCONHECIDO);
			Correntista correntista = capturaCorrentist(NUMERO_DESCONHECIDO);
			
			String validationReturn = validate(account);
			if (validationReturn == null) {
				boolean retornoRepositorio = repositoryAccount.include(account);
				if (retornoRepositorio) {
					System.out.println("---------------------------------------");
					System.out.println("Conta incluída com sucesso.");
				} else {
					System.out.println("---------------------------------------");
					System.out.println("Error: houve um erro, conta não incluída.");
				}
			} else {
				System.out.println(validationReturn);
			}		
		}
		
		private String validate(Account account) {
			int validacaoAccount = mediator.isAccount(account);
			if (validacaoAccount == 1) {
				System.out.println("---------------------------------------");
				return "Numero Invalido.";
			} else if (validacaoAccount == 2) {
				System.out.println("---------------------------------------");
				return "Status Invalido.";
			} else if (validacaoAccount == 3) {
				System.out.println("---------------------------------------");
				return "Data Abertura Invalida.";
			} else {
				return null;
			}
		}
		
		
		private Account capturaAccount(long numberDaAlteracao) {
			Account account;
			long numberAccount;
			if (numberDaAlteracao == NUMERO_DESCONHECIDO) {
				System.out.println("---------------------------------------");
				System.out.print("Digite o número da conta: ");
				numberAccount = ENTRADA.nextLong();			
			} else {
				numberAccount = numberDaAlteracao;
			}
			Correntista correntista = capturaCorrentista(numberAccount);
			System.out.print("Digite a data em que sua conta foi aberta [YYYY-MM-DD]: ");
			String dataAberturaString = ENTRADA.next();
			System.out.print("Selecione o tipo da conta (1, 2 ou 3): ");
			int codigoStatus = ENTRADA.nextInt();
			System.out.print("A conta é corrente ou poupança?(1 ou 2) ");
			int codigoPoupanca = ENTRADA.nextInt();
			boolean poupanca = false;
			if(codigoPoupanca == 1){
				poupanca = true;
			} else{
				poupanca = false;
			}
			account = new Account(numberAccount, codigoStatus, dataAberturaString, poupanca);
			account.setCorrentist(correntista);
			return account;
		}
		private Correntista capturaCorrentista(long numberAccount) {
			return null;
		}

		private Correntista capturaCorrentist(long numberAccount) {
			System.out.print("Digite o cpf do Correntista: ");
			String cpf = ENTRADA.next();
			System.out.print("Digite nome do Correntista: ");
			String correntistName = ENTRADA.next();
			return new Correntista(cpf, correntistName);
		}
		
		private void changeProcessing(long number) {
			Account account = repositoryAccount.search(number);
			String validationReturn = validate(account);
			if (validationReturn == null) {
				System.out.println("---------------------------------------");
				System.out.println("Digite a data de abertura para modificar (YYYY-MM-DD): ");
				String dataAberturaModificada = ENTRADA.next();
				boolean retornoRepositorio = repositoryAccount.change(dataAberturaModificada, account);
				if (retornoRepositorio) {
					System.out.println("---------------------------------------");
					System.out.println("Data de Abertura foi alterada com sucesso.");
				} else {
					System.out.println("---------------------------------------");
					System.out.println("Error: alteração da Data de abertura.");
				}
			} else {
				System.out.println(validationReturn);
			}		
		}
		
		private long searchProcessing() {
			System.out.println("---------------------------------------");
			System.out.print("Digite o number da conta: ");
			long number = ENTRADA.nextLong();
			Account account = repositoryAccount.search(number);
			if (account == null) {
				System.out.println("---------------------------------------");
				System.out.println("Conta não encontrada.");
				return NUMERO_DESCONHECIDO;
			} else {
				Correntista correntista = CorrentistaMediators.searchCorrentist(account.getCorrentista().getCpf());
				System.out.println("---------------------------------------");
				System.out.println("Numero da conta: " + account.getNumber());
				System.out.println("Data de Abertura da conta: " + account.getDataAbertura());
				System.out.println("Saldo disponível: " + account.getBalance());
				System.out.println("Status da conta: " + account.getStatusDescription());
				System.out.println("Score da conta: " + account.calcularEscore());
					
				if (account.isPoupanca()) {
					System.out.println("Taxa de juros: " + ((poupancaAccount) account).getTaxaJuros());
					System.out.println("Total depositos: " + ((poupancaAccount) account).getTotalDepositos());
				}
				System.out.println("Nome: " + correntista.getCorrentistName());
				System.out.println("Cpf: " + correntista.getCpf());
				return number;
			}
		}
		
		private void deleteProcessing(long number) {
			boolean retornoRepositorio = repositoryAccount.delete(number);
			if (retornoRepositorio) {
				System.out.println("---------------------------------------");
				System.out.println("Conta Excluida com sucesso.");
			} else {
				System.out.println("---------------------------------------");
				System.out.println("Error: Não foi possível Excluir conta.");
			}
		}
		
		private void closesProcessing(long number) {
			Account account = repositoryAccount.search(number);
			String validationReturn = validate(account);
			if (validationReturn == null) {
				System.out.println("---------------------------------------");
				System.out.println("Estamos Encerrando sua conta...");
				boolean retornoRepositorio = repositoryAccount.encerrar(account);
				if (retornoRepositorio) {
					System.out.println("---------------------------------------");
					System.out.println("Conta encerrada com sucesso");
				} else {
					System.out.println("---------------------------------------");
					System.out.println("Error: Conta não encerrada.");
				}
			} else {
				System.out.println(validationReturn);
			}		
		}
		
		private void blockProcessing(long number) {
			Account account = repositoryAccount.search(number);
			String validationReturn = validate(account);
			if (validationReturn == null) {
				System.out.println("---------------------------------------");
				System.out.println("Estamos bloqueando sua account...");
				boolean retornoRepositorio = repositoryAccount.bloquear(account);
				if (retornoRepositorio) {
					System.out.println("---------------------------------------");
					System.out.println("Conta bloqueada com sucesso.");
				} else {
					System.out.println("---------------------------------------");
					System.out.println("Error: Não foi possível bloquear conta.");
				}
			} else {
				System.out.println(validationReturn);
			}		
		}
		
		private void unblockProcessing(long number) {
			Account account = repositoryAccount.search(number);
			String validationReturn = validate(account);
			if (validationReturn == null) {
				System.out.println("---------------------------------------");
				System.out.println("Estamos bloqueando sua account...");
				boolean retornoRepositorio = repositoryAccount.desbloquear(account);
				if (retornoRepositorio) {
					System.out.println("---------------------------------------");
					System.out.println("Sua account foi desbloqueada com sucesso");
				} else {
					System.out.println("---------------------------------------");
					System.out.println("Erro: Não foi possível desbloquar conta.");
				}
			} else {
				System.out.println(validationReturn);
			}
		}
		
		private void creditorProcessing(long number) {
			Account account = repositoryAccount.search(number);
			
			String validationReturn = validate(account);
			if (validationReturn == null) {
				System.out.println("---------------------------------------");
				System.out.print("Quanto você deseja creditar da Account? ");
				double creditedValue = ENTRADA.nextDouble();
				boolean retornoRepositorio = repositoryAccount.creditarValor(creditedValue, account, mediator);
				if (retornoRepositorio) {
					System.out.println("---------------------------------------");
					System.out.println("Valor creditado com sucesso.");
				} else {
					System.out.println("---------------------------------------");
					System.out.println("Error: não foi possível creditar da sua conta.");
				}
			} else {
				System.out.println(validationReturn);
			}
		}
		
		private void debtorProcessing(long number) {
			Account account = repositoryAccount.search(number);

			
			String validationReturn = validate(account);
			if (validationReturn == null) {
				System.out.print("Quanto você deseja Debitar da Account? ");
				double debitedValue = ENTRADA.nextDouble();
				boolean retornoRepositorio = repositoryAccount.debitarCredito(debitedValue, account, mediator);
				if (retornoRepositorio) {
					System.out.println("---------------------------------------");
					System.out.println("Valor debitado com sucesso.");
				} else {
					System.out.println("---------------------------------------");
					System.out.println("Error: não foi possível debitar da sua conta.");
				}
			} else {
				System.out.println(validationReturn);
			}
		}
	}