package br.com.cesarschool.poo.geral;

import java.util.Scanner;

public class InterfaceAccount {
		
		private static final int NUMERO_DESCONHECIDO = -1;
		private final Scanner ENTRADA = new Scanner(System.in);
		private RepositorioConta repositorioConta = new RepositorioConta();
		
		public void executaTela() {
			while(true) {
				long numero = NUMERO_DESCONHECIDO;
				imprimeMenuPrincipal();
				int opcao = ENTRADA.nextInt();
				if (opcao == 1) {
					processaInclusao();
				} else if (opcao == 2) {
					numero = processaBusca();
					if (numero != NUMERO_DESCONHECIDO) {
						processaAlteracao(numero);
					} 
				} else if (opcao == 3) {
					numero = processaBusca();
					if (numero != NUMERO_DESCONHECIDO) {
						processaEncerrar(numero);
					}			
				} else if (opcao == 4) {
					numero = processaBusca();
					if (numero != NUMERO_DESCONHECIDO) {
						processaBloquear(numero);
					}
				} else if (opcao == 5) {
					numero = processaBusca();
					if (numero != NUMERO_DESCONHECIDO) {
						processaDesbloquear(numero);
					}
				} else if (opcao == 6) {
					numero = processaBusca();
					if (numero != NUMERO_DESCONHECIDO) {
						processaExclusao(numero);
					}
				} else if (opcao == 7) {
					processaBusca();
					
				} else if (opcao == 8) {
					numero = processaBusca();
					if (numero != NUMERO_DESCONHECIDO) {
						processaCreditar(numero);
					}
				} else if (opcao == 9) {
					numero = processaBusca();
					if (numero != NUMERO_DESCONHECIDO) {
						processaDebitar(numero);
					}
				} else if(opcao == 10) {
					System.out.println("Encerrando o processo de Cadastro de Contas!!");
					System.exit(0);
				} else {
					System.out.println("Op��o inv�lida!!");
				}
			} 
		}
		
		private void imprimeMenuPrincipal() {		
			System.out.println("1- Criar Conta");
			System.out.println("2- Alterar Conta");
			System.out.println("3- Encerrar Conta");
			System.out.println("4- Bloquear Conta");
			System.out.println("5- Desbloquear Conta");
			System.out.println("6- Excluir Conta");
			System.out.println("7- Buscar Conta");
			System.out.println("8- Creditar");
			System.out.println("9- Debitar");
			System.out.println("10- Sair");
			System.out.print("Digite a op��o: ");
		}
		
		private void processaInclusao() {
			Conta conta = capturaConta(NUMERO_DESCONHECIDO);
			String retornoValidacao = validar(conta);
			if (retornoValidacao == null) {
				boolean retornoRepositorio = repositorioConta.incluir(conta);
				if (retornoRepositorio) {
					System.out.println("Conta inclu�da com sucesso!");
				} else {
					System.out.println("Erro na inclus�o de conta!");
				}
			} else {
				System.out.println(retornoValidacao);
			}		
		}
		
		private String validar(Conta conta) {
			int validacaoConta = conta.isConta();
			if (validacaoConta == 1) {
				return "Numero Invalido!!";
			} else if (validacaoConta == 2) {
				return "Status Invalido!!";
			} else if (validacaoConta == 3) {
				return "Data Abertura Invalida!!";
			} else {
				return null;
			}
		}
		
		private Conta capturaConta(long numeroDaAlteracao) {
			long numeroConta;
			if (numeroDaAlteracao == NUMERO_DESCONHECIDO) {
				System.out.print("Digite o numero da conta: ");
				numeroConta = ENTRADA.nextLong();			
			} else {
				numeroConta = numeroDaAlteracao;
			}
			System.out.print("Digite a data em que sua conta foi aberta (YYYY-MM-DD): ");
			String dataAberturaString = ENTRADA.next();
			System.out.print("Digite o tipo da conta (1, 2 ou 3): ");
			int codigoStatus = ENTRADA.nextInt();
			StatusConta status = StatusConta.obterPorCodigo(codigoStatus);
			return new Conta(numeroConta, status, dataAberturaString);
		}

		private void processaAlteracao(long numero) {
			Conta conta = repositorioConta.buscar(numero);
			String retornoValidacao = validar(conta);
			if (retornoValidacao == null) {
				System.out.println("Digite a data de abertura para modificar (YYYY-MM-DD): ");
				String dataAberturaModificada = ENTRADA.next();
				boolean retornoRepositorio = repositorioConta.alterar(dataAberturaModificada, conta);
				if (retornoRepositorio) {
					System.out.println("Data de Abertura alterada com sucesso!");
				} else {
					System.out.println("Erro na altera��o da Data de abertura!");
				}
			} else {
				System.out.println(retornoValidacao);
			}		
		}
		
		private long processaBusca() {
			System.out.print("Digite o numero da conta: ");
			long numero = ENTRADA.nextLong();
			Conta conta = repositorioConta.buscar(numero);
			if (conta == null) {
				System.out.println("Conta n�o encontrada!");
				return NUMERO_DESCONHECIDO;
			} else {
				System.out.println("Numero Da conta: " + conta.getNumero());
				System.out.println("Data de Abertura da conta: " + conta.getDataAbertura());
				System.out.println("Saldo da Conta: " + conta.getSaldo());
				System.out.println("Status da conta: " + conta.getStatus().getDescricao());
				System.out.println("Score da Conta: " + conta.calcularEscore());
				return numero;
			}
		}
		
		private void processaExclusao(long numero) {
			boolean retornoRepositorio = repositorioConta.excluir(numero);
			if (retornoRepositorio) {
				System.out.println("Conta Excluida com sucesso!");
			} else {
				System.out.println("Erro no Exclus�o da Conta!");
			}
		}
		
		private void processaEncerrar(long numero) {
			Conta conta = repositorioConta.buscar(numero);
			String retornoValidacao = validar(conta);
			if (retornoValidacao == null) {
				System.out.println("Estamos Encerrando sua conta!");
				boolean retornoRepositorio = repositorioConta.encerrar(conta);
				if (retornoRepositorio) {
					System.out.println("Sua conta foi encerrada com sucesso");
				} else {
					System.out.println("Erro no encerramento da Conta!");
				}
			} else {
				System.out.println(retornoValidacao);
			}		
		}
		
		private void processaBloquear(long numero) {
			Conta conta = repositorioConta.buscar(numero);
			String retornoValidacao = validar(conta);
			if (retornoValidacao == null) {
				System.out.println("Estamos bloqueando sua conta!");
				boolean retornoRepositorio = repositorioConta.bloquear(conta);
				if (retornoRepositorio) {
					System.out.println("Sua conta foi bloqueada com sucesso");
				} else {
					System.out.println("Erro no bloqueamento da Conta!");
				}
			} else {
				System.out.println(retornoValidacao);
			}		
		}
		
		private void processaDesbloquear(long numero) {
			Conta conta = repositorioConta.buscar(numero);
			String retornoValidacao = validar(conta);
			if (retornoValidacao == null) {
				System.out.println("Estamos bloqueando sua conta!");
				boolean retornoRepositorio = repositorioConta.desbloquear(conta);
				if (retornoRepositorio) {
					System.out.println("Sua conta foi desbloqueada com sucesso");
				} else {
					System.out.println("Erro no desbloqueamento da Conta!");
				}
			} else {
				System.out.println(retornoValidacao);
			}
		}
		
		private void processaCreditar(long numero) {
			Conta conta = repositorioConta.buscar(numero);
			String retornoValidacao = validar(conta);
			if (retornoValidacao == null) {
				System.out.println("Quanto voc� deseja creditar da Conta?: ");
				double ValorCreditado = ENTRADA.nextDouble();
				boolean retornoRepositorio = repositorioConta.creditarValor(ValorCreditado, conta);
				if (retornoRepositorio) {
					System.out.println("O valor foi creditado com sucesso!!");
				} else {
					System.out.println("Erro na hora de creditar da sua conta!");
				}
			} else {
				System.out.println(retornoValidacao);
			}
		}
		
		private void processaDebitar(long numero) {
			Conta conta = repositorioConta.buscar(numero);
			String retornoValidacao = validar(conta);
			if (retornoValidacao == null) {
				System.out.println("Quanto voc� deseja Debitar da Conta?: ");
				double Valordebitado = ENTRADA.nextDouble();
				boolean retornoRepositorio = repositorioConta.debitar(Valordebitado, conta);
				if (retornoRepositorio) {
					System.out.println("O valor foi debitado com sucesso!");
				} else {
					System.out.println("Erro na hora de debitar da sua conta!");
				}
			} else {
				System.out.println(retornoValidacao);
			}
		}
	}
