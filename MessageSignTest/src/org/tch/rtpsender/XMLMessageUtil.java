package org.tch.rtpsender;



import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;



import org.w3c.dom.Document;

public class XMLMessageUtil {
	
	public List<String> ECHO_RESPONSE_MSG_BASE = Arrays.asList("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message xmlns=\"urn:tch\"><AppHdr><head:Fr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>",
			"</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:Fr><head:To xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>990000001S1</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:To><head:BizMsgIdr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">",
			"</head:BizMsgIdr><head:MsgDefIdr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">admn.006.001.01</head:MsgDefIdr><head:CreDt xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">",
			"</head:CreDt><head:Sgntr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"></head:Sgntr></AppHdr><EchoResponse><re:AdmnEchoResp xmlns:re=\"urn:iso:std:iso:20022:tech:xsd:admn.006.001.01\"><re:GrpHdr><re:MsgId>",
			"</re:MsgId><re:CreDtTm>",
			"</re:CreDtTm></re:GrpHdr><re:EchoResponse><re:InstgAgt><re:FinInstnId><re:ClrSysMmbId><re:MmbId>",
			"</re:MmbId></re:ClrSysMmbId></re:FinInstnId></re:InstgAgt><re:InstdAgt><re:FinInstnId><re:ClrSysMmbId><re:MmbId>990000001S1</re:MmbId></re:ClrSysMmbId></re:FinInstnId></re:InstdAgt><re:OrgnlInstrId>",
			"</re:OrgnlInstrId><re:FnctnCd>731</re:FnctnCd><re:TxSts>ACTC</re:TxSts></re:EchoResponse></re:AdmnEchoResp></EchoResponse></Message>");

	public List<String> ECHO_REQUEST_MSG_BASE = Arrays.asList("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message xmlns=\"urn:tch\"><AppHdr><head:Fr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>",
			"</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:Fr><head:To xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>990000001S1</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:To><head:BizMsgIdr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">",
			"</head:BizMsgIdr><head:MsgDefIdr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">admn.005.001.01</head:MsgDefIdr><head:CreDt xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">",
			"</head:CreDt><head:Sgntr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"></head:Sgntr></AppHdr><EchoRequest><er:AdmnEchoReq xmlns:er=\"urn:iso:std:iso:20022:tech:xsd:admn.005.001.01\"><er:GrpHdr><er:MsgId>",
			"</er:MsgId><er:CreDtTm>",
			"</er:CreDtTm></er:GrpHdr><er:EchoTxInf><er:FnctnCd>731</er:FnctnCd><er:InstrId>",
			"</er:InstrId><er:InstgAgt><er:FinInstnId><er:ClrSysMmbId><er:MmbId>",
			"</er:MmbId></er:ClrSysMmbId></er:FinInstnId></er:InstgAgt><er:InstdAgt><er:FinInstnId><er:ClrSysMmbId><er:MmbId>990000001S1</er:MmbId></er:ClrSysMmbId></er:FinInstnId></er:InstdAgt></er:EchoTxInf></er:AdmnEchoReq></EchoRequest></Message>");

	public List<String> SIGN_ON_MSG_BASE = Arrays.asList("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message xmlns=\"urn:tch\"><AppHdr><head:Fr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>",
			"</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:Fr><head:To xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>990000001S1</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:To><head:BizMsgIdr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">",
			"</head:BizMsgIdr><head:MsgDefIdr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">admn.001.001.01</head:MsgDefIdr><head:CreDt xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">",
			"</head:CreDt><head:Sgntr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"></head:Sgntr></AppHdr><SignOnRequest><sr:AdmnSignOnReq xmlns:sr=\"urn:iso:std:iso:20022:tech:xsd:admn.001.001.01\"><sr:GrpHdr><sr:MsgId>",
			"</sr:MsgId><sr:CreDtTm>",
			"</sr:CreDtTm></sr:GrpHdr><sr:SignOnReq><sr:InstrId>",
			"</sr:InstrId><sr:InstgAgt><sr:FinInstnId><sr:ClrSysMmbId><sr:MmbId>",
			"</sr:MmbId></sr:ClrSysMmbId></sr:FinInstnId></sr:InstgAgt><sr:InstdAgt><sr:FinInstnId><sr:ClrSysMmbId><sr:MmbId>990000001S1</sr:MmbId></sr:ClrSysMmbId></sr:FinInstnId></sr:InstdAgt></sr:SignOnReq></sr:AdmnSignOnReq></SignOnRequest></Message>");

	
	//th is wrong values
//	public List<String> SIGN_ON_MSG_BASE = Arrays.asList("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message xmlns=\"urn:tch\"><AppHdr><head:Fr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>",
//			"</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:Fr><head:To xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>990000001S1</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:To><head:BizMsgIdr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">",
//			"</head:BizMsgIdr><head:MsgDefIdr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">admn.001.001.01</head:MsgDefIdr><head:CreDt xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">",
//			"</head:CreDt><head:Sgntr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"></head:Sgntr></AppHdr><SignOnRequest><sr:AdmnSignOnReq xmlns:sr=\"urn:iso:std:iso:20022:tech:xsd:admn.001.001.01\"><sr:GrpHdr><sr:MsgId>",
//			"</sr:MsgId><sr:CreDtTm>",
//			"</sr:CreDtTm></sr:GrpHdr><sr:SignOnReq><sr:InstrId>",
//			"</sr:InstrId><sr:InstgAgt><sr:FinInstnId><sr:ClrSysMmbId><sr:MmbId>",
//			"</sr:MmbId></sr:ClrSysMmbId></sr:FinInstnId></sr:InstgAgt><sr:InstdAgt><sr:FinInstnId><sr:ClrSysMmbId></sr:ClrSysMmbId></sr:FinInstnId></sr:InstdAgt></sr:SignOnReq></sr:AdmnSignOnReq></SignOnRequest></Message>");

	
	public List<String> SIGN_OFF_MSG_BASE = Arrays.asList("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message xmlns=\"urn:tch\"><AppHdr><head:Fr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>",
			"</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:Fr><head:To xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>990000001S1</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:To><head:BizMsgIdr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">",
			"</head:BizMsgIdr><head:MsgDefIdr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">admn.003.001.01</head:MsgDefIdr><head:CreDt xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">",
			"</head:CreDt><head:Sgntr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"></head:Sgntr></AppHdr><SignOffRequest><sr:AdmnSignOffReq xmlns:sr=\"urn:iso:std:iso:20022:tech:xsd:admn.003.001.01\"><sr:GrpHdr><sr:MsgId>",
			"</sr:MsgId><sr:CreDtTm>",
			"</sr:CreDtTm></sr:GrpHdr><sr:SignOffReq><sr:InstrId>",
			"</sr:InstrId><sr:InstgAgt><sr:FinInstnId><sr:ClrSysMmbId><sr:MmbId>",
			"</sr:MmbId></sr:ClrSysMmbId></sr:FinInstnId></sr:InstgAgt><sr:InstdAgt><sr:FinInstnId><sr:ClrSysMmbId><sr:MmbId>990000001S1</sr:MmbId></sr:ClrSysMmbId></sr:FinInstnId></sr:InstdAgt></sr:SignOffReq></sr:AdmnSignOffReq></SignOffRequest></Message>");

	//this si swromg data
//	public List<String> SIGN_OFF_MSG_BASE = Arrays.asList("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message xmlns=\"urn:tch\"><AppHdr><head:Fr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>",
//			"</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:Fr><head:To xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>990000001S1</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:To><head:BizMsgIdr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">",
//			"</head:BizMsgIdr><head:MsgDefIdr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">admn.003.001.01</head:MsgDefIdr><head:CreDt xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">",
//			"</head:CreDt><head:Sgntr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"></head:Sgntr></AppHdr><SignOffRequest><sr:AdmnSignOffReq xmlns:sr=\"urn:iso:std:iso:20022:tech:xsd:admn.003.001.01\"><sr:GrpHdr><sr:MsgId>",
//			"</sr:MsgId><sr:CreDtTm>",
//			"</sr:CreDtTm></sr:GrpHdr><sr:SignOffReq><sr:InstrId>",
//			"</sr:InstrId><sr:InstgAgt><sr:FinInstnId><sr:ClrSysMmbId><sr:MmbId>",
//			"</sr:MmbId></sr:ClrSysMmbId></sr:FinInstnId></sr:InstgAgt><sr:InstdAgt><sr:FinInstnId><sr:ClrSysMmbId></sr:ClrSysMmbId></sr:FinInstnId></sr:InstdAgt></sr:SignOffReq></sr:AdmnSignOffReq></SignOffRequest></Message>");

	 public List<String> CREDIT_TRANSFER_MSG_BASE = Arrays.asList("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message xmlns=\"urn:tch\" xmlns:ct=\"urn:iso:std:iso:20022:tech:xsd:pacs.008.001.06\" xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"><AppHdr><head:Fr><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>",
             "</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:Fr><head:To><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>990000001S1</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:To><head:BizMsgIdr>",
             "</head:BizMsgIdr><head:MsgDefIdr>pacs.008.001.06</head:MsgDefIdr><head:CreDt>",
             "</head:CreDt><head:Sgntr></head:Sgntr></AppHdr><CreditTransfer><ct:FIToFICstmrCdtTrf><ct:GrpHdr><ct:MsgId>",
             "</ct:MsgId><ct:CreDtTm>",
             "</ct:CreDtTm><ct:NbOfTxs>1</ct:NbOfTxs><ct:TtlIntrBkSttlmAmt Ccy=\"USD\">3.00</ct:TtlIntrBkSttlmAmt><ct:IntrBkSttlmDt>",
             "</ct:IntrBkSttlmDt><ct:SttlmInf><ct:SttlmMtd>CLRG</ct:SttlmMtd><ct:ClrSys><ct:Cd>TCH</ct:Cd></ct:ClrSys></ct:SttlmInf></ct:GrpHdr><ct:CdtTrfTxInf><ct:PmtId><ct:InstrId>",
             "</ct:InstrId><ct:EndToEndId>MYREF123</ct:EndToEndId><ct:TxId>",
             "</ct:TxId><ct:ClrSysRef>001</ct:ClrSysRef></ct:PmtId><ct:PmtTpInf><ct:SvcLvl><ct:Cd>SDVA</ct:Cd></ct:SvcLvl><ct:LclInstrm><ct:Prtry>CONSUMER</ct:Prtry></ct:LclInstrm></ct:PmtTpInf><ct:IntrBkSttlmAmt Ccy=\"USD\">3.00</ct:IntrBkSttlmAmt><ct:ChrgBr>SLEV</ct:ChrgBr><ct:InstgAgt><ct:FinInstnId><ct:ClrSysMmbId><ct:MmbId>",
             "</ct:MmbId></ct:ClrSysMmbId></ct:FinInstnId></ct:InstgAgt><ct:InstdAgt><ct:FinInstnId><ct:ClrSysMmbId><ct:MmbId>",
             "</ct:MmbId></ct:ClrSysMmbId></ct:FinInstnId></ct:InstdAgt><ct:Dbtr><ct:Nm>",
             "</ct:Nm><ct:PstlAdr><ct:StrtNm>Route</ct:StrtNm><ct:BldgNb>66</ct:BldgNb><ct:PstCd>123456</ct:PstCd><ct:TwnNm>LA</ct:TwnNm><ct:Ctry>US</ct:Ctry></ct:PstlAdr><ct:Id><ct:PrvtId><ct:DtAndPlcOfBirth><ct:BirthDt>1980-12-06</ct:BirthDt><ct:CityOfBirth>NY</ct:CityOfBirth><ct:CtryOfBirth>US</ct:CtryOfBirth></ct:DtAndPlcOfBirth></ct:PrvtId></ct:Id></ct:Dbtr><ct:DbtrAcct><ct:Id><ct:Othr><ct:Id>02123456789012345</ct:Id></ct:Othr></ct:Id></ct:DbtrAcct><ct:DbtrAgt><ct:FinInstnId><ct:ClrSysMmbId><ct:MmbId>",
             "</ct:MmbId></ct:ClrSysMmbId></ct:FinInstnId></ct:DbtrAgt><ct:CdtrAgt><ct:FinInstnId><ct:ClrSysMmbId><ct:MmbId>",
             "</ct:MmbId></ct:ClrSysMmbId></ct:FinInstnId></ct:CdtrAgt><ct:Cdtr><ct:Nm>",
             "</ct:Nm><ct:PstlAdr><ct:StrtNm>Mircea Voda</ct:StrtNm><ct:BldgNb>40</ct:BldgNb><ct:PstCd>030669</ct:PstCd><ct:TwnNm>Bucuresti</ct:TwnNm><ct:Ctry>RO</ct:Ctry></ct:PstlAdr><ct:Id><ct:PrvtId><ct:DtAndPlcOfBirth><ct:BirthDt>1976-02-14</ct:BirthDt><ct:CityOfBirth>GL</ct:CityOfBirth><ct:CtryOfBirth>RO</ct:CtryOfBirth></ct:DtAndPlcOfBirth></ct:PrvtId></ct:Id></ct:Cdtr><ct:CdtrAcct><ct:Id><ct:Othr><ct:Id>04123456789012345</ct:Id></ct:Othr></ct:Id></ct:CdtrAcct><ct:RltdRmtInf><ct:RmtId>1234567890</ct:RmtId></ct:RltdRmtInf></ct:CdtTrfTxInf></ct:FIToFICstmrCdtTrf></CreditTransfer></Message>");
	 
	
	    public List<String> CT_MESSAGE_STATUS_REPORT_MSG_BASE = Arrays.asList("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message xmlns=\"urn:tch\" xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\" xmlns:ps=\"urn:iso:std:iso:20022:tech:xsd:pacs.002.001.07\"><AppHdr><head:Fr><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>",
	    		"</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:Fr><head:To><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>990000001S1</head:MmbId></head:ClrSysMmbId></head:FinInstnId><head:BrnchId><head:Id>",
	    		"</head:Id></head:BrnchId></head:FIId></head:To><head:BizMsgIdr>",
	    		"</head:BizMsgIdr><head:MsgDefIdr>pacs.002.001.07</head:MsgDefIdr><head:CreDt>",
	    		"</head:CreDt><head:Sgntr></head:Sgntr></AppHdr><MessageStatusReport><ps:FIToFIPmtStsRpt><ps:GrpHdr><ps:MsgId>",
	    		"</ps:MsgId><ps:CreDtTm>",
	    		"</ps:CreDtTm></ps:GrpHdr><ps:OrgnlGrpInfAndSts><ps:OrgnlMsgId>",
	    		"</ps:OrgnlMsgId><ps:OrgnlMsgNmId>pacs.008.001.06</ps:OrgnlMsgNmId><ps:OrgnlCreDtTm>",
	    		"</ps:OrgnlCreDtTm><ps:OrgnlNbOfTxs>1</ps:OrgnlNbOfTxs></ps:OrgnlGrpInfAndSts><ps:TxInfAndSts><ps:OrgnlInstrId>",
	    		"</ps:OrgnlInstrId><ps:OrgnlTxId>",
	    		"</ps:OrgnlTxId><ps:TxSts>",
	    		"</ps:TxSts><ps:AccptncDtTm>",
	    		"</ps:AccptncDtTm><ps:ClrSysRef>002</ps:ClrSysRef><ps:InstgAgt><ps:FinInstnId><ps:ClrSysMmbId><ps:MmbId>",
	    		"</ps:MmbId></ps:ClrSysMmbId></ps:FinInstnId></ps:InstgAgt><ps:InstdAgt><ps:FinInstnId><ps:ClrSysMmbId><ps:MmbId>",
	    		"</ps:MmbId></ps:ClrSysMmbId></ps:FinInstnId></ps:InstdAgt><ps:OrgnlTxRef><ps:IntrBkSttlmAmt Ccy=\"USD\">",
	    		"</ps:IntrBkSttlmAmt><ps:IntrBkSttlmDt>",
	    		"</ps:IntrBkSttlmDt></ps:OrgnlTxRef></ps:TxInfAndSts></ps:FIToFIPmtStsRpt></MessageStatusReport></Message>");

public List<String> RF_MESSAGE_STATUS_REPORT_MSG_BASE = Arrays.asList("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message xmlns=\"urn:tch\" xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\" xmlns:ps=\"urn:iso:std:iso:20022:tech:xsd:pacs.002.001.07\"><AppHdr><head:Fr><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>",
	    		"</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:Fr><head:To><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>990000001S1</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:To><head:BizMsgIdr>",
	    		"</head:BizMsgIdr><head:MsgDefIdr>pacs.002.001.07</head:MsgDefIdr><head:CreDt>",
	    		"</head:CreDt><head:Sgntr></head:Sgntr></AppHdr><MessageStatusReport><ps:FIToFIPmtStsRpt><ps:GrpHdr><ps:MsgId>",
	    		"</ps:MsgId><ps:CreDtTm>",
	    		"</ps:CreDtTm></ps:GrpHdr><ps:OrgnlGrpInfAndSts><ps:OrgnlMsgId>",
	    		"</ps:OrgnlMsgId><ps:OrgnlMsgNmId>camt.056.001.05</ps:OrgnlMsgNmId><ps:OrgnlCreDtTm>",
	    		"</ps:OrgnlCreDtTm><ps:OrgnlNbOfTxs>1</ps:OrgnlNbOfTxs></ps:OrgnlGrpInfAndSts><ps:TxInfAndSts><ps:OrgnlInstrId>",
	    		"</ps:OrgnlInstrId><ps:TxSts>",
	    		"</ps:TxSts><ps:AccptncDtTm>",
	    		"</ps:AccptncDtTm><ps:ClrSysRef>001</ps:ClrSysRef><ps:InstgAgt><ps:FinInstnId><ps:ClrSysMmbId><ps:MmbId>",
	    		"</ps:MmbId></ps:ClrSysMmbId></ps:FinInstnId></ps:InstgAgt><ps:InstdAgt><ps:FinInstnId><ps:ClrSysMmbId><ps:MmbId>990000001</ps:MmbId></ps:ClrSysMmbId></ps:FinInstnId></ps:InstdAgt><ps:OrgnlTxRef><ps:IntrBkSttlmAmt Ccy=\"USD\">",
	    		"</ps:IntrBkSttlmAmt><ps:IntrBkSttlmDt>",
	    		"</ps:IntrBkSttlmDt></ps:OrgnlTxRef></ps:TxInfAndSts></ps:FIToFIPmtStsRpt></MessageStatusReport></Message>");

public List<String> RETURN_OF_FUNDS_MSG_BASE = Arrays.asList("");

	 
	   private static final SimpleDateFormat _iso20022DateFieldFormatter = new SimpleDateFormat(new String("yyyy-MM-dd'T'HH:mm:ss"));
		private static final SimpleDateFormat _iso20022MessageDateNoTimeFieldFormatter = new SimpleDateFormat(new String("yyyy-MM-dd"));
	    private static final SimpleDateFormat _iso20022MessageDateTimeFieldFormatter = new SimpleDateFormat(new String("yyyy-MM-dd'T'HH:mm:ss.SSS"));
	    private static final SimpleDateFormat _iso20022MessageFieldDateFormatter = new SimpleDateFormat(new String("yyyyMMdd"));

	    
	    private String payerParticipantId;
	    private String payerRoutingNumber;
	    private String uniqueMessagePrefix;
	    
	    private String  currentInstructionId;
	    
	    
		public String getCurrentInstructionId() {
			
			//this.currentInstructionId = "" +  new Random().nextInt(34556677);
			
//			return "" +  new Random().nextInt(34556677);
			return currentInstructionId;
		}


		public void setCurrentInstructionId(String currentInstructionId) {
			this.currentInstructionId = currentInstructionId;
		}


		public XMLMessageUtil(String payerParticipantId, String payerRoutingNumber, String uniqueMessagePrefix) {
			//super();
			this.payerParticipantId = payerParticipantId;
			this.payerRoutingNumber = payerRoutingNumber;
			this.uniqueMessagePrefix = uniqueMessagePrefix;
			
			
		}
	    
	/*	
		public List<String> ECHO_REQUEST_MSG_BASE = Arrays.asList("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message xmlns=\"urn:tch\"><AppHdr><head:Fr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>"
				+ "</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:Fr><head:To xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"><head:FIId><head:FinInstnId><head:ClrSysMmbId><head:MmbId>990000001S1</head:MmbId></head:ClrSysMmbId></head:FinInstnId></head:FIId></head:To><head:BizMsgIdr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">"
				+ "</head:BizMsgIdr><head:MsgDefIdr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">admn.005.001.01</head:MsgDefIdr><head:CreDt xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\">"
				+ "</head:CreDt><head:Sgntr xmlns:head=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.01\"></head:Sgntr></AppHdr><EchoRequest><er:AdmnEchoReq xmlns:er=\"urn:iso:std:iso:20022:tech:xsd:admn.005.001.01\"><er:GrpHdr><er:MsgId>"
				+ "</er:MsgId><er:CreDtTm>"
				+ "</er:CreDtTm></er:GrpHdr><er:EchoTxInf><er:FnctnCd>731</er:FnctnCd><er:InstrId>"
				+ "</er:InstrId><er:InstgAgt><er:FinInstnId><er:ClrSysMmbId><er:MmbId>"
				+ "</er:MmbId></er:ClrSysMmbId></er:FinInstnId></er:InstgAgt><er:InstdAgt><er:FinInstnId><er:ClrSysMmbId><er:MmbId>990000001S1</er:MmbId></er:ClrSysMmbId></er:FinInstnId></er:InstdAgt></er:EchoTxInf></er:AdmnEchoReq></EchoRequest></Message>");
	     */
	    public String createEchoRequestMessage(int messageNumber, boolean digitallySign)
	    {
		    StringBuilder messageStringBuilder = new StringBuilder();
		    String messageNumberString = "";
		    
		    messageNumberString = String.format("%08d", messageNumber);
		    
		    String messageIdDatePart = _iso20022MessageFieldDateFormatter.format(new Date());
		    StringBuilder bizHeaderId = new StringBuilder();
		    bizHeaderId.append("B").append(messageIdDatePart).append(payerParticipantId).append("B").append(uniqueMessagePrefix).append(messageNumberString);
		    
		    SimpleDateFormat echoRequestMessageFieldDateFormatter = new SimpleDateFormat(new String("yyyyMMddHHmmss"));
		
		    StringBuilder messageId = new StringBuilder();
		    messageId.append(echoRequestMessageFieldDateFormatter.format(new Date())).append(payerParticipantId).append(uniqueMessagePrefix).append("0000");
		    currentInstructionId = messageId.toString();
		    
		    StringBuilder instructionId = new StringBuilder();
		    instructionId.append(messageIdDatePart).append(payerParticipantId).append("B0").append(uniqueMessagePrefix).append(messageNumberString);
		    
		    // Chunk unsigned SignOn XML message with ParticipantID interspersed where necessary
		    messageStringBuilder.append(ECHO_REQUEST_MSG_BASE.get(0));
		    messageStringBuilder.append(payerParticipantId);
		    messageStringBuilder.append(ECHO_REQUEST_MSG_BASE.get(1));
		    messageStringBuilder.append(bizHeaderId.toString());
		    messageStringBuilder.append(ECHO_REQUEST_MSG_BASE.get(2));
		    messageStringBuilder.append(_iso20022DateFieldFormatter.format(new Date()));
		    messageStringBuilder.append(ECHO_REQUEST_MSG_BASE.get(3));
		    messageStringBuilder.append(messageId);
		    messageStringBuilder.append(ECHO_REQUEST_MSG_BASE.get(4));
		    messageStringBuilder.append(_iso20022MessageDateTimeFieldFormatter.format(new Date()));
		    messageStringBuilder.append(ECHO_REQUEST_MSG_BASE.get(5));
		    messageStringBuilder.append(instructionId);
		    messageStringBuilder.append(ECHO_REQUEST_MSG_BASE.get(6));
		    messageStringBuilder.append(payerParticipantId);
		    messageStringBuilder.append(ECHO_REQUEST_MSG_BASE.get(7));
		    
		    String returnMessage = messageStringBuilder.toString();
		    
		    if (digitallySign)
		    {
		    	returnMessage = signXMLMessage(returnMessage);
		    }
		    
		    return returnMessage;
	    	
	    }
	    
	    public String createEchoResponseMessage(String originalInstructionId, int messageNumber, boolean digitallySign)
	    {
		    StringBuilder messageStringBuilder = new StringBuilder();
		    String messageNumberString = "";
		    
		    messageNumberString = String.format("%08d", messageNumber);
		    
		    String messageIdDatePart = _iso20022MessageFieldDateFormatter.format(new Date());
		    StringBuilder bizHeaderId = new StringBuilder();
		    bizHeaderId.append("B").append(messageIdDatePart).append(payerParticipantId).append("B").append(uniqueMessagePrefix).append(messageNumberString);
		    
		    SimpleDateFormat echoResponseMessageFieldDateFormatter = new SimpleDateFormat(new String("yyyyMMddHHmmss"));
		
		    StringBuilder messageId = new StringBuilder();
		    messageId.append(echoResponseMessageFieldDateFormatter.format(new Date())).append(payerParticipantId).append(uniqueMessagePrefix).append("0000");
		    currentInstructionId = messageId.toString();
		    
		    StringBuilder instructionId = new StringBuilder();
		    instructionId.append(messageIdDatePart).append(payerParticipantId).append("B0").append(uniqueMessagePrefix).append(messageNumberString);
		    
		    // Chunk unsigned SignOn XML message with ParticipantID interspersed where necessary
		    messageStringBuilder.append(ECHO_RESPONSE_MSG_BASE.get(0));
		    messageStringBuilder.append(payerParticipantId);
		    messageStringBuilder.append(ECHO_RESPONSE_MSG_BASE.get(1));
		    messageStringBuilder.append(bizHeaderId.toString());
		    messageStringBuilder.append(ECHO_RESPONSE_MSG_BASE.get(2));
		    messageStringBuilder.append(_iso20022DateFieldFormatter.format(new Date()));
		    messageStringBuilder.append(ECHO_RESPONSE_MSG_BASE.get(3));
		    messageStringBuilder.append(messageId);
		    messageStringBuilder.append(ECHO_RESPONSE_MSG_BASE.get(4));
		    messageStringBuilder.append(_iso20022MessageDateTimeFieldFormatter.format(new Date()));
		    messageStringBuilder.append(ECHO_RESPONSE_MSG_BASE.get(5));
		    messageStringBuilder.append(payerParticipantId);
		    messageStringBuilder.append(ECHO_RESPONSE_MSG_BASE.get(6));
		    messageStringBuilder.append(originalInstructionId);
		    messageStringBuilder.append(ECHO_RESPONSE_MSG_BASE.get(7));
		    
		    String returnMessage = messageStringBuilder.toString();
		    
		    if (digitallySign)
		    {
		    	returnMessage = signXMLMessage(returnMessage);
		    }
		    
		    return returnMessage;
	    	
	    }
	    
	    /**
	     * Create SignOnRequest (admn.001) XML message
	     * @param messageNumber Unique incrementing message number for the last 8 digits of the message ID
	     * @param digitallySign Apply XML Enveloped Signature
	     * @return SignOnRequest (admn.001) XML message
	     */
		public String createSignOnRequestMessage(int messageNumber, boolean digitallySign)
		{
			StringBuilder messageStringBuilder = new StringBuilder();
		    String messageNumberString = "";
		    
		    messageNumberString = String.format("%08d", messageNumber);
		    
		    String messageIdDatePart = _iso20022MessageFieldDateFormatter.format(new Date());
		    StringBuilder bizHeaderId = new StringBuilder();
		    bizHeaderId.append("B").append(messageIdDatePart).append(payerParticipantId).append("B").append(uniqueMessagePrefix).append(messageNumberString);
		    
		    SimpleDateFormat signOnMessageFieldDateFormatter = new SimpleDateFormat(new String("yyyyMMddHHmmss"));
		
		    StringBuilder messageId = new StringBuilder();
		    messageId.append(signOnMessageFieldDateFormatter.format(new Date())).append(payerParticipantId).append(uniqueMessagePrefix).append("0000");
		    
		    StringBuilder instructionId = new StringBuilder();
			instructionId.append(messageIdDatePart).append(payerParticipantId).append("B0").append(uniqueMessagePrefix).append(messageNumberString);
		    currentInstructionId = instructionId.toString();
		    
		    // Chunk unsigned SignOn XML message with ParticipantID interspersed where necessary
		    messageStringBuilder.append(SIGN_ON_MSG_BASE.get(0));
		    messageStringBuilder.append(payerParticipantId);
		    messageStringBuilder.append(SIGN_ON_MSG_BASE.get(1));
		    messageStringBuilder.append(bizHeaderId.toString());
		    messageStringBuilder.append(SIGN_ON_MSG_BASE.get(2));
		    messageStringBuilder.append(_iso20022DateFieldFormatter.format(new Date()));
		    messageStringBuilder.append(SIGN_ON_MSG_BASE.get(3));
		    messageStringBuilder.append(messageId);
		    messageStringBuilder.append(SIGN_ON_MSG_BASE.get(4));
		    messageStringBuilder.append(_iso20022MessageDateTimeFieldFormatter.format(new Date()));
		    messageStringBuilder.append(SIGN_ON_MSG_BASE.get(5));
		    messageStringBuilder.append(instructionId);
		    messageStringBuilder.append(SIGN_ON_MSG_BASE.get(6));
		    messageStringBuilder.append(payerParticipantId);
		    messageStringBuilder.append(SIGN_ON_MSG_BASE.get(7));
		    
		    String returnMessage = messageStringBuilder.toString();
		    
		    if (digitallySign)
		    {
		    	returnMessage = signXMLMessage(returnMessage);
		    }
		    
		    return returnMessage;
	    }
	    
	    /**
	     * Create SignOffRequest (admn.003) XML message
	     * @param messageNumber Unique incrementing message number for the last 8 digits of the message ID
	     * @param digitallySign Apply XML Enveloped Signature
	     * @return SignOffRequest (admn.003) XML message
	     */
		public String createSignOffRequestMessage(int messageNumber, boolean digitallySign)
		{
			StringBuilder messageStringBuilder = new StringBuilder();
		    String messageNumberString = "";
		    
		    messageNumberString = String.format("%08d", messageNumber);
		    
		    String messageIdDatePart = _iso20022MessageFieldDateFormatter.format(new Date());
		    StringBuilder bizHeaderId = new StringBuilder();
		    bizHeaderId.append("B").append(messageIdDatePart).append(payerParticipantId).append("B").append(uniqueMessagePrefix).append(messageNumberString);
		    
		    SimpleDateFormat signOnMessageFieldDateFormatter = new SimpleDateFormat(new String("yyyyMMddHHmmss"));
		
		    StringBuilder messageId = new StringBuilder();
		    messageId.append(signOnMessageFieldDateFormatter.format(new Date())).append(payerParticipantId).append(uniqueMessagePrefix).append("0000");
		    
		    StringBuilder instructionId = new StringBuilder();
			instructionId.append(messageIdDatePart).append(payerParticipantId).append("B0").append(uniqueMessagePrefix).append(messageNumberString);
		    currentInstructionId = instructionId.toString();
		    
		    // Chunk unsigned SignOff XML message with ParticipantID interspersed where necessary
		    messageStringBuilder.append(SIGN_OFF_MSG_BASE.get(0));
		    messageStringBuilder.append(payerParticipantId);
		    messageStringBuilder.append(SIGN_OFF_MSG_BASE.get(1));
		    messageStringBuilder.append(bizHeaderId.toString());
		    messageStringBuilder.append(SIGN_OFF_MSG_BASE.get(2));
		    messageStringBuilder.append(_iso20022DateFieldFormatter.format(new Date()));
		    messageStringBuilder.append(SIGN_OFF_MSG_BASE.get(3));
		    messageStringBuilder.append(messageId);
		    messageStringBuilder.append(SIGN_OFF_MSG_BASE.get(4));
		    messageStringBuilder.append(_iso20022MessageDateTimeFieldFormatter.format(new Date()));
		    messageStringBuilder.append(SIGN_OFF_MSG_BASE.get(5));
		    messageStringBuilder.append(instructionId);
		    messageStringBuilder.append(SIGN_OFF_MSG_BASE.get(6));
		    messageStringBuilder.append(payerParticipantId);
		    messageStringBuilder.append(SIGN_OFF_MSG_BASE.get(7));
		    
		    String returnMessage = messageStringBuilder.toString();
		    
		    if (digitallySign)
		    {
		    	returnMessage = signXMLMessage(returnMessage);
		    }
		    
		    return returnMessage;
	    }
	    
		/**
		 * Create Message Status Report (pacs.002) XML message for Credit Transfer (pacs.008)
		 * @param payeeParticipantId 11 character payee participant ID
		 * @param payeeRoutingNumber 9 digit payee routing number
		 * @param originalMessageId Message ID from corresponding Credit Transfer (pacs.008) message
		 * @param originalInstructionId Instruction ID from corresponding Credit Transfer (pacs.008) message
		 * @param originalDate Date from corresponding Credit Transfer (pacs.008) message
		 * @param amount Amount of corresponding Credit Transfer (pacs.008) message
		 * @param messageNumber Unique incrementing message number for the last 8 digits of the message ID
		 * @param digitallySign Apply XML Enveloped Signature
		 * @return Message Status Report (pacs.002) XML message
		 */
	    public String createMessageStatusReportMessage(String payerParticipantId, String payerRoutingNumber, String payeeParticipantId, String payeeRoutingNumber, String originalMessageId, String originalInstructionId, String originalDate, String amount, int messageNumber, boolean digitallySign)
	    {
	        StringBuilder messageStringBuilder = new StringBuilder();
	        String messageNumberString = "";
	 
		    String messageIdDatePart = _iso20022MessageFieldDateFormatter.format(new Date());
		    StringBuilder bizHeaderId = new StringBuilder();
		    bizHeaderId.append("B").append(messageIdDatePart).append(payerParticipantId).append("B").append(uniqueMessagePrefix).append(messageNumberString);

	        messageNumberString = String.format("%08d", messageNumber);
	        
	        String messageId = "M" + _iso20022MessageFieldDateFormatter.format(new Date()) + payerParticipantId + "B" + uniqueMessagePrefix + messageNumberString;
		    
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(0));
		    messageStringBuilder.append(payeeParticipantId);
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(1));
		    messageStringBuilder.append(payerParticipantId);
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(2));
		    messageStringBuilder.append(bizHeaderId.toString());
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(3));
		    messageStringBuilder.append(_iso20022DateFieldFormatter.format(new Date()));
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(4));
	        messageStringBuilder.append(messageId);
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(5));
	        messageStringBuilder.append(_iso20022DateFieldFormatter.format(new Date()));
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(6));
	        messageStringBuilder.append(originalMessageId);
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(7));
	        messageStringBuilder.append(originalDate);
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(8));
	        messageStringBuilder.append(originalInstructionId);
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(9));
	        messageStringBuilder.append(originalInstructionId);
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(10));
	        messageStringBuilder.append("ACTC");
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(11));
	        messageStringBuilder.append(_iso20022DateFieldFormatter.format(new Date()));
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(12));
	        messageStringBuilder.append(payerRoutingNumber);
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(13));
	        messageStringBuilder.append(payeeRoutingNumber);  // RTP Switch
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(14));
	        messageStringBuilder.append(amount);
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(15));
	        messageStringBuilder.append(_iso20022MessageDateNoTimeFieldFormatter.format(new Date()));
		    messageStringBuilder.append(CT_MESSAGE_STATUS_REPORT_MSG_BASE.get(16));

		    String returnMessage = messageStringBuilder.toString();
		    
		    if (digitallySign)
		    {
		    	returnMessage = signXMLMessage(returnMessage);
		    }
		    
		    return returnMessage;
	    }
	    
		/**
		 * Create Message Status Report (pacs.002) XML message
		 * @param payeeParticipantId 11 character payee participant ID
		 * @param payeeRoutingNumber 9 digit payee routing number
		 * @param originalMessageId Message ID from corresponding Credit Transfer (pacs.008) message
		 * @param originalInstructionId Instruction ID from corresponding Credit Transfer (pacs.008) message
		 * @param originalDate Date from corresponding Credit Transfer (pacs.008) message
		 * @param amount Amount of corresponding Credit Transfer (pacs.008) message
		 * @param messageNumber Unique incrementing message number for the last 8 digits of the message ID
		 * @param digitallySign Apply XML Enveloped Signature
		 * @return Message Status Report (pacs.002) XML message
		 */
	    public String createReturnOfFundsMessageStatusReportMessage(String payerParticipantId, String payerRoutingNumber, String payeeParticipantId, String payeeRoutingNumber, String originalMessageId, String originalInstructionId, String originalDate, String amount, String interbankSettlementDate, int messageNumber, boolean digitallySign)
	    {
	        StringBuilder messageStringBuilder = new StringBuilder();
	        String messageNumberString = "";
	 
		    String messageIdDatePart = _iso20022MessageFieldDateFormatter.format(new Date());
		    StringBuilder bizHeaderId = new StringBuilder();
		    bizHeaderId.append("B").append(messageIdDatePart).append(payerParticipantId).append("B").append(uniqueMessagePrefix).append(messageNumberString);

	        messageNumberString = String.format("%08d", messageNumber);
	        
	        String messageId = "M" + _iso20022MessageFieldDateFormatter.format(new Date()) + payerParticipantId + "B" + uniqueMessagePrefix + messageNumberString;
		    
		    messageStringBuilder.append(RF_MESSAGE_STATUS_REPORT_MSG_BASE.get(0));
		    messageStringBuilder.append(payeeParticipantId);
		    messageStringBuilder.append(RF_MESSAGE_STATUS_REPORT_MSG_BASE.get(1));
//		    messageStringBuilder.append(payerParticipantId);
//		    messageStringBuilder.append(RF_MESSAGE_STATUS_REPORT_MSG_BASE.get(2));
		    messageStringBuilder.append(bizHeaderId.toString());
		    messageStringBuilder.append(RF_MESSAGE_STATUS_REPORT_MSG_BASE.get(2));
		    messageStringBuilder.append(_iso20022DateFieldFormatter.format(new Date()));
		    messageStringBuilder.append(RF_MESSAGE_STATUS_REPORT_MSG_BASE.get(3));
	        messageStringBuilder.append(messageId);
		    messageStringBuilder.append(RF_MESSAGE_STATUS_REPORT_MSG_BASE.get(4));
	        messageStringBuilder.append(_iso20022DateFieldFormatter.format(new Date()));
		    messageStringBuilder.append(RF_MESSAGE_STATUS_REPORT_MSG_BASE.get(5));
	        messageStringBuilder.append(originalMessageId);
		    messageStringBuilder.append(RF_MESSAGE_STATUS_REPORT_MSG_BASE.get(6));
	        messageStringBuilder.append(originalDate);
		    messageStringBuilder.append(RF_MESSAGE_STATUS_REPORT_MSG_BASE.get(7));
	        messageStringBuilder.append(originalInstructionId);
		    messageStringBuilder.append(RF_MESSAGE_STATUS_REPORT_MSG_BASE.get(8));
	        messageStringBuilder.append("ACTC");
		    messageStringBuilder.append(RF_MESSAGE_STATUS_REPORT_MSG_BASE.get(9));
	        messageStringBuilder.append(_iso20022DateFieldFormatter.format(new Date()));
		    messageStringBuilder.append(RF_MESSAGE_STATUS_REPORT_MSG_BASE.get(10));
	        messageStringBuilder.append(payeeRoutingNumber);
		    messageStringBuilder.append(RF_MESSAGE_STATUS_REPORT_MSG_BASE.get(11));
	        messageStringBuilder.append(amount);
		    messageStringBuilder.append(RF_MESSAGE_STATUS_REPORT_MSG_BASE.get(12));
	        messageStringBuilder.append(interbankSettlementDate);
		    messageStringBuilder.append(RF_MESSAGE_STATUS_REPORT_MSG_BASE.get(13));

		    String returnMessage = messageStringBuilder.toString();
		    
		    if (digitallySign)
		    {
		    	returnMessage = signXMLMessage(returnMessage);
		    }
		    
		    return returnMessage;
	    }
	    
	    /**
	     * Create a Credit Transfer (pacs.008) XML message
	     * <br>NOTE: Some needed fields were passed into the XMLMessageUtil constructor so are not needed as arguments
	     * @param payeeRoutingNumber 9 digit payee routing number
	     * @param messageNumber Unique incrementing message number for the last 8 digits of the message ID
	     * @param digitallySign Apply XML Enveloped Signature
	     * @return Credit Transfer (pacs.008) XML message
	     */
	    public String createCreditTransferRequestMessage(String payeeRoutingNumber, int messageNumber, boolean digitallySign)
	    {
	        StringBuilder messageStringBuilder = new StringBuilder();
	        String messageNumberString = "";
	        
	        messageNumberString = String.format("%08d", messageNumber);
	        
	        String messageId = "M" + _iso20022MessageFieldDateFormatter.format(new Date()) + payerParticipantId + "B" + uniqueMessagePrefix + messageNumberString;
	        String instructionId = _iso20022MessageFieldDateFormatter.format(new Date()) + payerParticipantId + "B" + uniqueMessagePrefix + messageNumberString;
	        currentInstructionId = instructionId;
	        
	        // Chunk unsigned CreditTransfer XML message with ParticipantID interspersed where necessary
	        messageStringBuilder.append(CREDIT_TRANSFER_MSG_BASE.get(0));
	        messageStringBuilder.append(payerParticipantId);
	        messageStringBuilder.append(CREDIT_TRANSFER_MSG_BASE.get(1));
	        messageStringBuilder.append("B").append(_iso20022MessageFieldDateFormatter.format(new Date())).append(payerParticipantId).append("B").append(uniqueMessagePrefix).append(messageNumberString);
	        messageStringBuilder.append(CREDIT_TRANSFER_MSG_BASE.get(2));
	        messageStringBuilder.append(_iso20022DateFieldFormatter.format(new Date()));
	        messageStringBuilder.append(CREDIT_TRANSFER_MSG_BASE.get(3));
	        messageStringBuilder.append(messageId);
	        messageStringBuilder.append(CREDIT_TRANSFER_MSG_BASE.get(4));
	        messageStringBuilder.append(_iso20022DateFieldFormatter.format(new Date()));
	        messageStringBuilder.append(CREDIT_TRANSFER_MSG_BASE.get(5));
	        messageStringBuilder.append(_iso20022MessageDateNoTimeFieldFormatter.format(new Date()));
	        messageStringBuilder.append(CREDIT_TRANSFER_MSG_BASE.get(6));
	        messageStringBuilder.append(instructionId);
	        messageStringBuilder.append(CREDIT_TRANSFER_MSG_BASE.get(7));
	        messageStringBuilder.append(instructionId);
	        messageStringBuilder.append(CREDIT_TRANSFER_MSG_BASE.get(8));
	        messageStringBuilder.append(payerRoutingNumber);
	        messageStringBuilder.append(CREDIT_TRANSFER_MSG_BASE.get(9));
	        messageStringBuilder.append(payeeRoutingNumber);
	        messageStringBuilder.append(CREDIT_TRANSFER_MSG_BASE.get(10));
	        messageStringBuilder.append(payerRoutingNumber);
	        messageStringBuilder.append(CREDIT_TRANSFER_MSG_BASE.get(11));
	        messageStringBuilder.append(payerRoutingNumber);
	        messageStringBuilder.append(CREDIT_TRANSFER_MSG_BASE.get(12));
	        messageStringBuilder.append(payeeRoutingNumber);
	        messageStringBuilder.append(CREDIT_TRANSFER_MSG_BASE.get(13));
	        messageStringBuilder.append(payeeRoutingNumber);
	        messageStringBuilder.append(CREDIT_TRANSFER_MSG_BASE.get(14));
	    	
		    String returnMessage = messageStringBuilder.toString();
		    
		    if (digitallySign)
		    {
		    	returnMessage = signXMLMessage(returnMessage);
		    }
		    
		    return returnMessage;
	    }
	    
	    /**
	     * Helper to find a value given a CSV list of the path to the tag.
	     * <br>Example: To get the payee participant ID in a leg 2 pacs.008 message the XML tag list would be 
	     * <br><code>      <br>"head:To,head:FIId,head:FinInstnId,head:ClrSysMmbId,head:MmbId"</code>
	     * @param xmlMessage Full XML message
	     * @param xmlTagList String of CSV list of XML tags to find element
	     * @return
	     */
	    public static String findXMLValue(String xmlMessage, String xmlTagList)
		{
			String returnValue = "";
			
			String[] xmlTags = xmlTagList.split(",");
			
			// Some XML tag names are reused in subsections of a unique parent tag.
			// To disect these chunks the code has to loop over the xmlTagList
			// Isolating layer by layer until the last xmlTagList element which will
			// provide the desired value.
			for (String xmlTag : xmlTags)
			{
				//System.out.println("Tag: " + xmlTag + " XML: " + xmlMessage);
				// Break XML string into chunks to isolate a given value
				if ((xmlMessage != null) && (!xmlMessage.isEmpty()))
				{
					// Break XML message into a "head" and "tail" using the opening XML tag
					// The head is discarded. The tail is the tag's value and the rest of the XML message
					String[] xmlChunks = xmlMessage.split("<" + xmlTag + ">");
					
					if ((xmlChunks != null) && (xmlChunks.length == 2))
					{
						// Now separate the tag value from the closing tag.
						// The head is the value and the tail is discarded.
						
						// One quick clean up before splitting the message is to remove any "attribute"
						// values that were part of the XML tag to search for. The attribute portion
						// was necessary for the opening tag but is not present in the closing tag.
						String[] closingTagWithoutAttr = xmlTag.split(" ");
						String[] xmlChunks2 = xmlChunks[1].split("</" + closingTagWithoutAttr[0] + ">");
						
						if (xmlChunks2 != null)
						{
							// The desired value is now the head.
							// Note that if there are multiple XML tags specified in the xmlTagList argument
							// the head will be an XML subsection that needs further disection.
							returnValue = xmlChunks2[0];
							//System.out.println("XML Chunk: " + returnValue);
						}
					}
				}
				
				// Not be done here if there are are more values in xmlTagList argument. Reuse the xmlMessage arg
				// but it will not be just the subsection of the original message that was obtained above.
				xmlMessage = returnValue;
			}
			
			return returnValue;
		}
	    
	    /**
	     * Apply software-based XML enveloped signature to XML message.
	     * @param xmlMessage Message needing signature
	     * @return Signed XML message
	     */
		private String signXMLMessage(String xmlMessage)
		{
			// Creating a new signer for every XML message. When trying to reuse this object the signatures of 2 - N documents were corrupt.
			SignatureExample _signer = new SignatureExample();
	        Document xmlDoc = _signer.unmarshalXML(xmlMessage);

	        String returnValue = _signer.getSignedXMLMessageString(xmlDoc);

	        return returnValue;
		}

		/**
		 * Unit test code. Not used in finished application
		 * @param args
		 */


}
