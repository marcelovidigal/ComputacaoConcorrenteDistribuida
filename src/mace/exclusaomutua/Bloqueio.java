package mace.exclusaomutua;

public interface Bloqueio {
	public void requisitarSecaoCritica(int pid); // deve bloquear
	public void liberarSecaoCritica(int pid);
}