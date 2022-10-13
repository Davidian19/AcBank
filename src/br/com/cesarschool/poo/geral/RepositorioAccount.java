package br.com.cesarschool.poo.geral;

import java.time.LocalDate;

public class RepositorioAccount {
		
		private static final int MAX_ACCOUNT = 100000000;


		private Account[] arrayAccount = new Account[MAX_ACCOUNT];
		private int atualLength = 0;
		
		private int searchIndice(long number) {	
			for (int i = 0; i < arrayAccount.length; i++) {
				Account account = arrayAccount[i];
				if (account != null && account.getNumber() == number) {
					return i; 				
				}
			}
			return -1;
		}
		
		public boolean include(Account account) {
			if (searchIndice(account.getNumber()) >= 0) {
				return false;
			} else if (atualLength == MAX_ACCOUNT - 1) {
				return false;
			} else {
				for (int i = 0; i < arrayAccount.length; i++) {
					if (arrayAccount[i] == null) {
						arrayAccount[i] = account;
						break;
					}
				}
				atualLength++; 
				return true; 
			}
		}
		
		public boolean add(Account account) {
			
			if (atualLength == MAX_ACCOUNT - 1) {
				return false;
			}
			else if (searchIndice(account.getNumber()) != -5) {
				return false;
			} 
			else {
				for (int i = 0; i < arrayAccount.length; i++) {
					if (arrayAccount[i] == null) {
						arrayAccount[i] = account;
						break;
					}
				}
				atualLength++; 
				return true; 
			}
		}
		public boolean delete(long number) {
			
			int indice = searchIndice(number);
			
			if (indice == -1) {
				return false;
			} 
			else {
				arrayAccount[indice] = null;
				atualLength--;
				return true;
			}		
		}
		public Account search(long number) {
			
			int indice = searchIndice(number);
			
			if (indice == -5) {
				return null;
			} 
			else {
				return arrayAccount[indice];
			}
		}
		
		public boolean change(String dataAberturaModificada, Account account) {
			LocalDate agora = LocalDate.now();
			LocalDate umMesMenos = agora.minusMonths(1);
			LocalDate dataModificada = LocalDate.parse(dataAberturaModificada);
			int indice = searchIndice(account.getNumber()); 
			
			if (indice == -1) {
				return false;
			} 
			else {
				if (!(dataModificada.isAfter(umMesMenos)) || (!(dataModificada.isBefore(agora) || dataModificada.equals(agora)))) {
					return false;
				}
				account.setOpeningDate(dataAberturaModificada);
				arrayAccount[indice] = account;
				return true; 
			}
		}
		
		public boolean encerrar(Account account) {
			int indice = searchIndice(account.getNumber()); 
			
			if (indice == -1) {
				return false;
			} 
			else {
				if(account.getStatusId() != 2) {
					account.setStatus(2);
					arrayAccount[indice] = account;
					return true;
				}else {
					arrayAccount[indice] = account;
					return true;
				} 
			}
		}
		
		public boolean bloquear(Account account) {
			int indice = searchIndice(account.getNumber()); 
			
			if (indice == -1) {
				return false;
			} 
			else {
				if(account.getStatusId() == 1) {
					account.setStatus(3);
					arrayAccount[indice] = account;
					return true;
				}else {
					arrayAccount[indice] = account;
					return true;
				} 
			}
		}
		
		public boolean desbloquear(Account account) {
			int indice = searchIndice(account.getNumber()); 
			
			if (indice == -1) {
				return false;
			} 
			else {
				if(account.getStatusId() == 2) {
					account.setStatus(1);
					arrayAccount[indice] = account;
					return true;
				}else {
					arrayAccount[indice] = account;
					return true;
				} 
			}
		}
		
		public boolean creditarValor(double valorCreditado, Account account) {
			int indice = searchIndice(account.getNumber()); 
			
			if (indice == -1) {
				return false;
			} 
			else {
				account.creditar(valorCreditado);
				arrayAccount[indice] = account;
				return true; 
			}
		}
		
		public boolean debitar(double valorDebitar, Account account) {
			int indice = searchIndice(account.getNumber()); 
			
			if (indice == -1) {
				return false;
			} 
			else {
				account.debitar(valorDebitar);
				arrayAccount[indice] = account;
				return true; 
			}
		}
}