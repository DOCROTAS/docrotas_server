package br.com.docrotas.server.service.cte;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.groovy.util.StringUtil;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import br.com.docrotas.server.entity.Cte;
import br.com.docrotas.server.entity.Empresa;
import br.com.docrotas.server.entity.EnderecoPessoa;
import br.com.docrotas.server.entity.Pessoa;
import br.com.docrotas.server.entity.TipoAmbienteEmissao;
import br.com.docrotas.server.service.TipoPessoaCTe;
import br.com.docrotas.server.utils.DocumentoEletronicoUtils;

public class GeradorXmlCte {
	
	private static final Logger log = Logger.getLogger(GeradorXmlCte.class);
	
	private static final SimpleDateFormat yyMM = new SimpleDateFormat("yyMM");
	private static final String MODELO_DOCUMENTO_CTE = "57";
	private static final String VERSAO_APLICACAO = "DOCROTAS";
	private static final String MODAL_RODOVIARIO = "01";
	private static final String VERSAO = "3.00";
	private static final String NOME_HOMOLOGACAO = "CT-E EMITIDO EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL";
	private static final String HOMOLOCACAO = "2";
	private static final String RETRATO = "1";
	
	public Document getDocumentXML(Cte cte) throws Exception{
		Document documentCte = null;
		
		if (cte == null) {
			throw new Exception("Cte não informado.");
		}
		
		if (StringUtils.isEmpty(cte.getChaveAcesso())) {
			atualizarChaveAcesso(cte);
		}

		Element enviCTe = new Element("enviCTe");
		enviCTe.setAttribute("versao", VERSAO);
		Element idLote = new Element("idLote");
		idLote.addContent(String.valueOf(cte.getId()));
		Element elementCTe = new Element("CTe");
		elementCTe.addContent(getElementInfCTe(cte));
		
		enviCTe.addContent(idLote);
		enviCTe.addContent(elementCTe);

		documentCte = new Document();
		documentCte.setRootElement(enviCTe);

		log.info("XML Lote CT-e sem assinatura: " + new XMLOutputter(Format.getPrettyFormat()).outputString(documentCte));

		return documentCte;
	}
	
	public Document getConsReciCTe(String numRecibo, TipoAmbienteEmissao ambiente) {
		Document document = null;

		Element consReciCTe = new Element("consReciCTe");
		consReciCTe.setAttribute("versao", VERSAO);

//		Element versao = new Element("versao");
//		versao.addContent(VERSAO);
//		consReciCTe.addContent(versao);

		Element tpAmb = new Element("tpAmb");
		tpAmb.addContent(ambiente.getCodigo());
		consReciCTe.addContent(tpAmb);

		Element nRec = new Element("nRec");
		nRec.addContent(numRecibo);
		consReciCTe.addContent(nRec);
		
		document = new Document();
		document.setRootElement(consReciCTe);
		
		log.info("XML Consulta Processamento de Lote de CT-e: " + new XMLOutputter(Format.getPrettyFormat()).outputString(document));

		return document;
	}

	private void atualizarChaveAcesso(Cte cte) {
		cte.setChaveAcesso(gerarChaveAcesso(cte));
	}

	private String gerarChaveAcesso(Cte cte) {
		StringBuilder stbChave = new StringBuilder();
		stbChave.append(cte.getEmpresa().getEndereco().getCidade().getUf().getCodIBGE().toString());
		stbChave.append(yyMM.format(cte.getDtCriacao()));
		stbChave.append(cte.getEmpresa().getCnpj());
		stbChave.append(MODELO_DOCUMENTO_CTE);
		stbChave.append(StringUtils.leftPad(cte.getSerie().toString(), 3, '0'));
		stbChave.append(StringUtils.leftPad(cte.getNumero().toString(), 9, '0'));
		stbChave.append("1");
		stbChave.append(StringUtils.leftPad(cte.getNumero().toString(), 8, '0'));
		stbChave.append(String.valueOf(getDigitoVerificador(stbChave.toString())));
		
		return stbChave.toString();
	}

	private String getNumeroAleatorio() {
		Double numero = Math.random() * 10000000;
		long numeroAleatorio = numero.longValue();
		return String.valueOf(numeroAleatorio);
	}

	private int getDigitoVerificador(String chave) {
		int total = 0;
		int peso = 2;

		for (int i = chave.length() - 1; i >= 0; i--){
			int valor = 0;
			
			if (peso > 9) {
				peso = 2;
			}
			
			int digito = Integer.parseInt(String.valueOf(chave.charAt(i)));
			valor = digito * peso;
			
			total = total + valor;

			peso++;
		}

		int resto = total % 11;

		return (resto == 0 || resto == 1) ? 0 : (11 - resto);
	}
	
	private Element getElementInfCTe(Cte cte) {
		String identificacao = "CTe" + cte.getChaveAcesso();
		
		Element infCTe = new Element("infCte");

		infCTe.setAttribute("Id", identificacao);
		infCTe.setAttribute("versao", VERSAO);

		infCTe.addContent(getElementIde(cte));
		//infCTe.addContent(getElementCompl(cte));
		infCTe.addContent(getElementEmit(cte.getEmpresa()));
		infCTe.addContent(getElementPessoa(TipoPessoaCTe.REMETENTE, cte.getRemetente(), TipoAmbienteEmissao.HOMOLOGACAO));
		infCTe.addContent(getElementPessoa(TipoPessoaCTe.DESTINATARIO, cte.getDestinatario(),  TipoAmbienteEmissao.HOMOLOGACAO));
		infCTe.addContent(getElementVprest(cte));
		infCTe.addContent(getElementImp(cte));
		infCTe.addContent(getElementInfCteNorm(cte));

		return infCTe;
	}

	private Element getElementIde(Cte cte) {
		Element elementIde = new Element("ide");
		
		Element elementCuf = new Element("cUF");
		elementCuf.addContent(String.valueOf(cte.getEmpresa().getEndereco().getCidade().getUf().getCodIBGE()));
		elementIde.addContent(elementCuf);
		
		Element elementCct = new Element("cCT");
		elementCct.addContent(StringUtils.leftPad(String.valueOf(cte.getNumero()),8,"0"));
		elementIde.addContent(elementCct);
		
		Element elementCfop = new Element("CFOP");
		elementCfop.addContent("5932");
		elementIde.addContent(elementCfop);
		
		Element elementNatOp = new Element("natOp");
		elementNatOp.addContent(String.valueOf(cte.getCfop().getDescricao()));
		elementIde.addContent(elementNatOp);
		
		Element elementMod = new Element("mod");
		elementMod.addContent(MODELO_DOCUMENTO_CTE);
		elementIde.addContent(elementMod);

		Element elementSerie = new Element("serie");
		elementSerie.addContent(String.valueOf(cte.getSerie()));
		elementIde.addContent(elementSerie);
		
		Element elementNCT = new Element("nCT");
		elementNCT.addContent(String.valueOf(cte.getNumero()));
		elementIde.addContent(elementNCT);

		Element elementDhEmi = new Element("dhEmi");
		elementDhEmi.addContent(DocumentoEletronicoUtils.formatDateComFusoHorario(cte.getDtCriacao()));
		elementIde.addContent(elementDhEmi);

		Element elementTpImp = new Element("tpImp");
		elementTpImp.addContent(RETRATO);
		elementIde.addContent(elementTpImp);

		//por enquanto "1-normal" (4-EPEC SVC/5-Contingencia FSDA/7-SVC_RS/8-SVC_SP)
		Element elementTpEmis = new Element("tpEmis");
		elementTpEmis.addContent("1");
		elementIde.addContent(elementTpEmis);
		
		Element elementCDV = new Element("cDV");
		elementCDV.addContent(cte.getChaveAcesso().substring(43,44));
		elementIde.addContent(elementCDV);

		//--Ambiente 2-homologação (1-produção)
		Element elementTpAmb = new Element("tpAmb");
		elementTpAmb.addContent(HOMOLOCACAO);
		elementIde.addContent(elementTpAmb);

		Element elementTpCte = new Element("tpCTe");
		elementTpCte.addContent("0");
		elementIde.addContent(elementTpCte);

		//Emitido por aplicativo do contribuinte 
		Element elementProcEmi = new Element("procEmi");
		elementProcEmi.addContent("0");
		elementIde.addContent(elementProcEmi);

		Element elementVerProc = new Element("verProc");
		elementVerProc.addContent(VERSAO_APLICACAO);
		elementIde.addContent(elementVerProc);

		Element elementCMunEnv = new Element("cMunEnv");
		elementCMunEnv.addContent(String.valueOf(cte.getEmpresa().getEndereco().getCidade().getCodIBGE()));
		elementIde.addContent(elementCMunEnv);

		Element elementXMunEnv = new Element("xMunEnv");
		elementXMunEnv.addContent(cte.getEmpresa().getEndereco().getCidade().getNome());
		elementIde.addContent(elementXMunEnv);

		Element elementUfEnv = new Element("UFEnv");
		elementUfEnv.addContent(cte.getEmpresa().getEndereco().getCidade().getUf().getSigla());
		elementIde.addContent(elementUfEnv);

		Element elementModal = new Element("modal");
		elementModal.addContent(MODAL_RODOVIARIO);
		elementIde.addContent(elementModal);

		Element elementTpServ = new Element("tpServ");
		elementTpServ.addContent("0");
		elementIde.addContent(elementTpServ);

		EnderecoPessoa enderecoRemetente = null;
		
		for (EnderecoPessoa endereco : cte.getRemetente().getEnderecoPessoa()) {
			enderecoRemetente = endereco;
		}
		
		EnderecoPessoa enderecoDestinatario = null;
		
		for (EnderecoPessoa endereco : cte.getDestinatario().getEnderecoPessoa()) {
			enderecoDestinatario = endereco;
		}

		Element elementCMunIni = new Element("cMunIni");
		elementCMunIni.addContent(String.valueOf(enderecoRemetente.getCidade().getCodIBGE()));
		elementIde.addContent(elementCMunIni);

		Element elementXMunIni = new Element("xMunIni");
		elementXMunIni.addContent(enderecoRemetente.getCidade().getNome());
		elementIde.addContent(elementXMunIni);

		Element elementUfIni = new Element("UFIni");
		elementUfIni.addContent(enderecoRemetente.getCidade().getUf().getSigla());
		elementIde.addContent(elementUfIni);

		Element elementCMunFim = new Element("cMunFim");
		elementCMunFim.addContent(String.valueOf(enderecoDestinatario.getCidade().getCodIBGE()));
		elementIde.addContent(elementCMunFim);
		
		Element elementXMunFim = new Element("xMunFim");
		elementXMunFim.addContent(enderecoDestinatario.getCidade().getNome());
		elementIde.addContent(elementXMunFim);
		
		Element elementUfFim = new Element("UFFim");
		elementUfFim.addContent(enderecoDestinatario.getCidade().getUf().getSigla());
		elementIde.addContent(elementUfFim);
		
		//padrão 1... cliente não retira mercadoria
		Element elementRetira = new Element("retira");
		elementRetira.addContent("1");
		elementIde.addContent(elementRetira);
		
		//--montar rotina de verificação
		Element elementIndIEToma = new Element("indIEToma");
		elementIndIEToma.addContent("1");
		elementIde.addContent(elementIndIEToma);
		
		//--montar rotina de verificação if toma3 = remetente/expedidor/recebedor/destinatario
		Element elementToma3 = new Element("toma3");
		Element elementToma = new Element("toma");
		//--verificar se tomador é remetente/expedidor/recebedor/destinatario
		elementToma.addContent("0");
		elementToma3.addContent(elementToma);
		elementIde.addContent(elementToma3);
		
		//--else toma4(consignatário)
		//--
		//--
		//--
		
		//--montar rotina de verificação de entrada em contingêcia
		//Element elementDhCont
		//Element elementXJust
		
		return elementIde;
	}
	
	private Element getElementCompl(Cte cte) {
		//Entrega
		Element elementCompl = new Element("compl");
		
		Element elementSemData = new Element("semData");
		Element elementTpPer = new Element("tpPer");
		//Sem data definida
		elementTpPer.addContent("0"); 
		elementSemData.addContent(elementTpPer);
		
		elementCompl.addContent(elementSemData);
		
		Element elementSemHora = new Element("semHora");
		Element elementTpHor = new Element("tpHor");
		//Sem horário definido
		elementTpHor.addContent("0");
		elementSemHora.addContent(elementTpHor);
		
		elementCompl.addContent(elementSemHora);
		
		return elementCompl;
	}
	
	private Element getElementEmit(Empresa empresa) {
		Element elementEmit = new Element("emit");
		
		Element elementCnpj = new Element("CNPJ");
		elementCnpj.addContent(empresa.getCnpj());
		elementEmit.addContent(elementCnpj);
		
		Element elementIe = new Element("IE");
		elementIe.addContent(empresa.getIe());
		elementEmit.addContent(elementIe);
		
		Element elementXnome = new Element("xNome");
		elementXnome.addContent(empresa.getRazao());
		elementEmit.addContent(elementXnome);
		
		if(empresa.getFantasia() != null){
			Element elementXfant = new Element("xFant");
			elementXfant.addContent(empresa.getFantasia());
			elementEmit.addContent(elementXfant);
		}
		
		Element elementEnderEmit = new Element("enderEmit");
		
		Element elementXlgr = new Element("xLgr");
		elementXlgr.addContent(empresa.getEndereco().getLogradouro());
		elementEnderEmit.addContent(elementXlgr);
		
		Element elementNro = new Element("nro");
		elementNro.addContent(String.valueOf(empresa.getEndereco().getNumero()));
		elementEnderEmit.addContent(elementNro);
		
		Element elementXbairro = new Element("xBairro");
		elementXbairro.addContent(empresa.getEndereco().getBairro());
		elementEnderEmit.addContent(elementXbairro);
		
		Element elementCmun = new Element("cMun");
		elementCmun.addContent(String.valueOf(empresa.getEndereco().getCidade().getCodIBGE()));
		elementEnderEmit.addContent(elementCmun);
		
		Element elementXmun = new Element("xMun");
		elementXmun.addContent(empresa.getEndereco().getCidade().getNome());
		elementEnderEmit.addContent(elementXmun);
		
		Element elementCep = new Element("CEP");
		elementCep.addContent(empresa.getEndereco().getCep());
		elementEnderEmit.addContent(elementCep);
		
		Element elementUf = new Element("UF");
		elementUf.addContent(empresa.getEndereco().getCidade().getUf().getSigla());
		elementEnderEmit.addContent(elementUf);
		
		elementEmit.addContent(elementEnderEmit);
		
		return elementEmit;
	}	
	
	private Element getElementPessoa(TipoPessoaCTe tipoPessoaCTe, Pessoa pessoa, TipoAmbienteEmissao tipoAmbienteEmissao) {
		
		Element elementPessoa;
	
		elementPessoa = new Element(tipoPessoaCTe.getTag());
		
		if(pessoa.getCpfCnpj().length() == 14){
			Element elementCnpj = new Element("CNPJ");
			elementCnpj.addContent(pessoa.getCpfCnpj());
			elementPessoa.addContent(elementCnpj);
			
			Element elementIe = new Element("IE");
			elementIe.addContent(pessoa.getIe());
			elementPessoa.addContent(elementIe);				
		}else{
			Element elementCpf = new Element("CPF");
			elementCpf.addContent(pessoa.getCpfCnpj());
			elementPessoa.addContent(elementCpf);			
		}
		
		Element elementXnome = new Element("xNome");
		
		if (TipoAmbienteEmissao.HOMOLOGACAO.equals(tipoAmbienteEmissao)) {
			elementXnome.addContent(NOME_HOMOLOGACAO);
		} else {
			elementXnome.addContent(pessoa.getNome());
		}
		elementPessoa.addContent(elementXnome);
		
		if(TipoPessoaCTe.REMETENTE.equals(tipoPessoaCTe) && pessoa.getFantasia() != null){
			Element elementXfant = new Element("xFant");
			elementXfant.addContent(pessoa.getFantasia());
			elementPessoa.addContent(elementXfant);
		}
		
		Element elementEnder;
		if(tipoPessoaCTe.equals(TipoPessoaCTe.REMETENTE)){
			elementEnder = new Element("enderReme");
		}else{
			elementEnder = new Element("enderDest");
		}
		
		EnderecoPessoa endereco = null;
		
		for (EnderecoPessoa enderecoP : pessoa.getEnderecoPessoa()) {
			endereco = enderecoP;
		}
		
		Element elementXlgr = new Element("xLgr");
		elementXlgr.addContent(endereco.getLogradouro());
		elementEnder.addContent(elementXlgr);
		
		Element elementNro = new Element("nro");
		elementNro.addContent(String.valueOf(endereco.getNumero()));
		elementEnder.addContent(elementNro);
		
		Element elementXbairro = new Element("xBairro");
		elementXbairro.addContent(endereco.getBairro());
		elementEnder.addContent(elementXbairro);
		
		Element elementCmun = new Element("cMun");
		elementCmun.addContent(String.valueOf(endereco.getCidade().getCodIBGE()));
		elementEnder.addContent(elementCmun);
		
		Element elementXmun = new Element("xMun");
		elementXmun.addContent(endereco.getCidade().getNome());
		elementEnder.addContent(elementXmun);
		
		Element elementCep = new Element("CEP");
		elementCep.addContent(endereco.getCep());
		elementEnder.addContent(elementCep);
		
		Element elementUf = new Element("UF");
		elementUf.addContent(endereco.getCidade().getUf().getSigla());
		elementEnder.addContent(elementUf);
		
		//tabela código país BACEN
		Element elementCpais = new Element("cPais");
		elementCpais.addContent("1058");
		elementEnder.addContent(elementCpais);
		
		Element elementXpais = new Element("xPais");
		elementXpais.addContent("BRASIL");
		elementEnder.addContent(elementXpais);
		
		//Email... 
		
		elementPessoa.addContent(elementEnder);

		return elementPessoa;
	}
	
	private Element getElementVprest(Cte cte) {
		Element elementVprest = new Element("vPrest");
		
		Element elementVtprest = new Element("vTPrest");
		elementVtprest.addContent(DocumentoEletronicoUtils.formatDouble(cte.getVlrFrete(), 2));
		elementVprest.addContent(elementVtprest);
		
		Element elementVrec = new Element("vRec");;
		elementVrec.addContent(DocumentoEletronicoUtils.formatDouble(cte.getVlrFrete(), 2));
		elementVprest.addContent(elementVrec);
		
		/*--Detalhamento--
 
		Comp
		xNome
		vComp
		
		*/
		
		return elementVprest;
	}
	
	private Element getElementImp(Cte cte) {
		//por enquanto ICMS00
		Element elementImp = new Element("imp");
		
		Element elementIcms = new Element("ICMS");
		
		Element elementIcms00 = new Element("ICMS00");
		
		//conforme CST do CTe
		Element elementCst = new Element("CST");
		elementCst.addContent("00");
		elementIcms00.addContent(elementCst);
		
		Element elementVbc = new Element("vBC");
		elementVbc.addContent(DocumentoEletronicoUtils.formatDouble(cte.getBaseCalculo(),2));
		elementIcms00.addContent(elementVbc);
		
		Element elementPicms = new Element("pICMS");
		elementPicms.addContent(DocumentoEletronicoUtils.formatDouble(cte.getAliquota(), 2));
		elementIcms00.addContent(elementPicms);
		
		Element elementVicms = new Element("vICMS");
		elementVicms.addContent(DocumentoEletronicoUtils.formatDouble(cte.getVlrIcms(), 2));
		elementIcms00.addContent(elementVicms);
		
		elementIcms.addContent(elementIcms00);
		elementImp.addContent(elementIcms);
		
		return elementImp;
	}	
	
	private Element getElementInfCteNorm(Cte cte) {
		Element elementInfCteNorm = new Element("infCTeNorm");
		
		Element elementInfCarga = new Element("infCarga");
		
		Element elementVcarga = new Element("vCarga");
		elementVcarga.addContent(DocumentoEletronicoUtils.formatDouble(cte.getVlrMercadoria(),2));
		elementInfCarga.addContent(elementVcarga);
		
		Element elementProPred = new Element("proPred");
		elementProPred.addContent(cte.getProduto());
		elementInfCarga.addContent(elementProPred);
		
		Element elementInfQ = new Element("infQ");
		
		Element elementCunid = new Element("cUnid");
		elementCunid.addContent("00");
		elementInfQ.addContent(elementCunid);
		
		Element elementTpMed = new Element("tpMed");
		elementTpMed.addContent("LITRAGEM");
		elementInfQ.addContent(elementTpMed);
		
		Element elementQcarga = new Element("qCarga");
		elementQcarga.addContent("1000");
		elementInfQ.addContent(elementQcarga);
		
		elementInfCarga.addContent(elementInfQ);
		elementInfCteNorm.addContent(elementInfCarga);
		
		Element elementInfdoc = new Element("infDoc");

		Element elementInfnfe = new Element("infNFe");			
		Element elementChave = new Element("chave");
		elementChave.addContent("31181026314062000161552570000015121961307232");
		elementInfnfe.addContent(elementChave);
		elementInfdoc.addContent(elementInfnfe);
		elementInfCteNorm.addContent(elementInfdoc);
		
		
		Element elementInfmodal = new Element("infModal");
		Attribute atributeVersao = new Attribute("versaoModal", "3.00");
		elementInfmodal.setAttribute(atributeVersao);
		
		//Modal Rodovíario
		Element elementRodo = new Element("rodo");
		
		Element elementRntrc = new Element("RNTRC");
		elementRntrc.addContent("00705790");
		elementRodo.addContent(elementRntrc);
		
		elementInfmodal.addContent(elementRodo);
		
		elementInfCteNorm.addContent(elementInfmodal);
		
		return elementInfCteNorm;
	}	
	
}
