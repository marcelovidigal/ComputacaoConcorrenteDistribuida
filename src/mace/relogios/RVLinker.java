package mace.relogios;

import mace.classesutilitarias.Util;
import mace.programacaodistribuida.Linker;
import mace.programacaodistribuida.Msg;

public class RVLinker extends Linker {
	
	public RelogioVetorial rv;
	
	int[] tagRecebida = null;
	
	public RVLinker(String nomebase, int id, int n) throws Exception {
		super(nomebase, id, n);
		
		rv = new RelogioVetorial(n, id);
		tagRecebida = new int[n];
	}
	
	public void enviarMsg(int destino, String tag, String msg) {
		super.enviarMsg(destino , " vector " , rv.toString());
		super.enviarMsg(destino, tag, msg);
		
		rv.enviar();
	}
	
	public void enviarMsgPadrao(int destino, String tag , String msg) {
		super.enviarMsg(destino , tag , msg);
	}
	
	public Msg receberMsg(int origem) throws java.io.IOException {
		
		Msg msg1 = super.receberMsg(origem);
		
		if (msg1.getTag().equals(" vector ")) {
			Util.readArray(msg1.toString(), tagRecebida);
			rv.receber(tagRecebida);
			Msg msg2 = super.receberMsg(origem); // mensagem app
			
			return msg2;
		} else
			return msg1;
	}
	
}