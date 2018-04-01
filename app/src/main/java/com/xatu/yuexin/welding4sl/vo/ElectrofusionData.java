package com.xatu.yuexin.welding4sl.vo;


/*
 * 电熔详细数据信息
 */
/**
 * @author Administrator
 *
 */
public class ElectrofusionData  {
	
	private int eFDId;
	private String eNo;    
	private String eDate;    
	private String eHour;    
	private String eCompany;    
	private String eNorme;
	private String ePrimaryV;    
	private String eDirection;    
	private String ePrj_manager;        
	private String eConstruction;    
	private String eTemp;
	private String eMode;    
	private String eBrand;    
	private String eType;    
	private String eDiameter;    
	private String eSDR;    
	private String eRcode;    
	private String eRmeas;    
	private String ePos;    
	private String eVolt;    
	private String eEnergie;    
	private String eTime;    
	private String eNomdt;    
	private String eElas;    
	private String eRefresh;
	private String eResult;    
	private String eCheckerAcceptor;    
	private String eName;    
	private String eDiam1xDiam2;
	private String eTp;    
	private String eRegul;    
	private String eNom;    
	private String eAdju;    
	private String eP;    
	private String eEr;    
	private String eManag;    
	private String eProj;    
	private String eOperat;    
	private String eLocal;    
	private String eInfo;    
	private String eFIId;
	private String efIversion;

	private String EGPS_UTC;  
	private String EGPS_latitude;    
	private String EGPS_longitude;   
	private String EGPS_status;    
	private String EGPS_high;
	
	private String elastMaintenance;
	private String ebarcodeRES;
	private String emesuredRES;
	private String escraping;
	private String einstrumentNo;
	
	private String ex;
	private String ey;
	private String ez;
	private String eh;
	private String edwd;
	
	private String  jianli="";//监理员
	private String  zhijian="";//质检员
	private String note;
	
	
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getJianli() {
		
		return jianli;
	}
	public void setJianli(String jianli) {
		this.jianli = jianli;
	}
	public String getZhijian() {
		
		return zhijian;
	}
	public void setZhijian(String zhijian) {
		this.zhijian = zhijian;
	}
	public String getEx() {
		return ex;
	}
	public void setEx(String ex) {
		this.ex = ex;
	}
	public String getEy() {
		return ey;
	}
	public void setEy(String ey) {
		this.ey = ey;
	}
	public String getEz() {
		return ez;
	}
	public void setEz(String ez) {
		this.ez = ez;
	}
	public String getEh() {
		return eh;
	}
	public void setEh(String eh) {
		this.eh = eh;
	}
	public String getEdwd() {
		return edwd;
	}
	public void setEdwd(String edwd) {
		this.edwd = edwd;
	}
	public String getElastMaintenance() {
		return elastMaintenance;
	}
	public void setElastMaintenance(String elastMaintenance) {
		this.elastMaintenance = elastMaintenance;
	}
	public String getEbarcodeRES() {
		return ebarcodeRES;
	}
	public void setEbarcodeRES(String ebarcodeRES) {
		this.ebarcodeRES = ebarcodeRES;
	}
	public String getEmesuredRES() {
		return emesuredRES;
	}
	public void setEmesuredRES(String emesuredRES) {
		this.emesuredRES = emesuredRES;
	}
	public String getEscraping() {
		return escraping;
	}
	public void setEscraping(String escraping) {
		this.escraping = escraping;
	}
	
	public String getEinstrumentNo() {
		return einstrumentNo;
	}
	public void setEinstrumentNo(String einstrumentNo) {
		this.einstrumentNo = einstrumentNo;
	}
	public int geteFDId() {
		return eFDId;
	}
	public void seteFDId(int eFDId) {
		this.eFDId = eFDId;
	}
	public String geteNo() {
		return eNo;
	}
	public void seteNo(String eNo) {
		this.eNo = eNo;
	}
	public String geteDate() {
		return eDate;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
	public String geteHour() {
		return eHour;
	}
	public void seteHour(String eHour) {
		this.eHour = eHour;
	}
	public String geteCompany() {
		return eCompany;
	}
	public void seteCompany(String eCompany) {
		this.eCompany = eCompany;
	}
	public String geteNorme() {
		return eNorme;
	}
	public void seteNorme(String eNorme) {
		this.eNorme = eNorme;
	}
	public String getePrimaryV() {
		return ePrimaryV;
	}
	public void setePrimaryV(String ePrimaryV) {
		this.ePrimaryV = ePrimaryV;
	}
	public String geteDirection() {
		return eDirection;
	}
	public void seteDirection(String eDirection) {
		this.eDirection = eDirection;
	}
	public String getePrj_manager() {
		return ePrj_manager;
	}
	public void setePrj_manager(String ePrjManager) {
		ePrj_manager = ePrjManager;
	}
	public String geteConstruction() {
		return eConstruction;
	}
	public void seteConstruction(String eConstruction) {
		this.eConstruction = eConstruction;
	}
	public String geteTemp() {
		return eTemp;
	}
	public void seteTemp(String eTemp) {
		this.eTemp = eTemp;
	}
	public String geteMode() {
		return eMode;
	}
	public void seteMode(String eMode) {
		this.eMode = eMode;
	}
	public String geteBrand() {
		return eBrand;
	}
	public void seteBrand(String eBrand) {
		this.eBrand = eBrand;
	}
	public String geteType() {
		return eType;
	}
	public void seteType(String eType) {
		this.eType = eType;
	}
	public String geteDiameter() {
		return eDiameter;
	}
	public void seteDiameter(String eDiameter) {
		this.eDiameter = eDiameter;
	}
	public String geteSDR() {
		return eSDR;
	}
	public void seteSDR(String eSDR) {
		this.eSDR = eSDR;
	}
	public String geteRcode() {
		return eRcode;
	}
	public void seteRcode(String eRcode) {
		this.eRcode = eRcode;
	}
	public String geteRmeas() {
		return eRmeas;
	}
	public void seteRmeas(String eRmeas) {
		this.eRmeas = eRmeas;
	}
	public String getePos() {
		return ePos;
	}
	public void setePos(String ePos) {
		this.ePos = ePos;
	}
	public String geteVolt() {
		return eVolt;
	}
	public void seteVolt(String eVolt) {
		this.eVolt = eVolt;
	}
	public String geteEnergie() {
		return eEnergie;
	}
	public void seteEnergie(String eEnergie) {
		this.eEnergie = eEnergie;
	}
	public String geteTime() {
		return eTime;
	}
	public void seteTime(String eTime) {
		this.eTime = eTime;
	}
	public String geteNomdt() {
		return eNomdt;
	}
	public void seteNomdt(String eNomdt) {
		this.eNomdt = eNomdt;
	}
	public String geteElas() {
		return eElas;
	}
	public void seteElas(String eElas) {
		this.eElas = eElas;
	}
	public String geteRefresh() {
		return eRefresh;
	}
	public void seteRefresh(String eRefresh) {
		this.eRefresh = eRefresh;
	}
	public String geteResult() {
		return eResult;
	}
	public void seteResult(String eResult) {
		this.eResult = eResult;
	}
	public String geteCheckerAcceptor() {
		return eCheckerAcceptor;
	}
	public void seteCheckerAcceptor(String eCheckerAcceptor) {
		if(eCheckerAcceptor.length()>=8){
			this.jianli = eCheckerAcceptor.substring(eCheckerAcceptor.length()-4,eCheckerAcceptor.length());
			this.zhijian = eCheckerAcceptor.substring(0,4);
		}
		this.eCheckerAcceptor = eCheckerAcceptor;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String geteDiam1xDiam2() {
		return eDiam1xDiam2;
	}
	public void seteDiam1xDiam2(String eDiam1xDiam2) {
		this.eDiam1xDiam2 = eDiam1xDiam2;
	}
	public String geteTp() {
		return eTp;
	}
	public void seteTp(String eTp) {
		this.eTp = eTp;
	}
	public String geteRegul() {
		return eRegul;
	}
	public void seteRegul(String eRegul) {
		this.eRegul = eRegul;
	}
	public String geteNom() {
		return eNom;
	}
	public void seteNom(String eNom) {
		this.eNom = eNom;
	}
	public String geteAdju() {
		return eAdju;
	}
	public void seteAdju(String eAdju) {
		this.eAdju = eAdju;
	}
	public String geteP() {
		return eP;
	}
	public void seteP(String eP) {
		this.eP = eP;
	}
	public String geteEr() {
		return eEr;
	}
	public void seteEr(String eEr) {
		this.eEr = eEr;
	}
	public String geteManag() {
		return eManag;
	}
	public void seteManag(String eManag) {
		this.eManag = eManag;
	}
	public String geteOperat() {
		return eOperat;
	}
	public void seteOperat(String eOperat) {
		this.eOperat = eOperat;
	}
	public String geteLocal() {
		return eLocal;
	}
	public void seteLocal(String eLocal) {
		this.eLocal = eLocal;
	}
	public String geteInfo() {
		return eInfo;
	}
	public void seteInfo(String eInfo) {
		this.eInfo = eInfo;
	}
	public String geteFIId() {
		return eFIId;
	}
	public void seteFIId(String eFIId) {
		this.eFIId = eFIId;
	}
	
	public String getEGPS_UTC() {
		return EGPS_UTC;
	}
	public void setEGPS_UTC(String eGPSUTC) {
		EGPS_UTC = eGPSUTC;
	}
	public String getEGPS_latitude() {
		return EGPS_latitude;
	}
	public void setEGPS_latitude(String eGPSLatitude) {
		EGPS_latitude = eGPSLatitude;
	}
	public String getEGPS_longitude() {
		return EGPS_longitude;
	}
	public void setEGPS_longitude(String eGPSLongitude) {
		EGPS_longitude = eGPSLongitude;
	}
	public String getEGPS_status() {
		return EGPS_status;
	}
	public void setEGPS_status(String eGPSStatus) {
		EGPS_status = eGPSStatus;
	}
	public String getEGPS_high() {
		return EGPS_high;
	}
	public void setEGPS_high(String eGPSHigh) {
		EGPS_high = eGPSHigh;
	}
	public int getEFDId() {
		return eFDId;
	}
	public void setEFDId(int id) {
		eFDId = id;
	}
	public String getENo() {
		return eNo;
	}
	public void setENo(String no) {
		eNo = no;
	}
	public String getEDate() {
		return eDate;
	}
	public void setEDate(String date) {
		eDate = date;
	}
	public String getEHour() {
		return eHour;
	}
	public void setEHour(String hour) {
		eHour = hour;
	}
	public String getECompany() {
		return eCompany;
	}
	public void setECompany(String company) {
		eCompany = company;
	}
	public String getENorme() {
		return eNorme;
	}
	public void setENorme(String norme) {
		eNorme = norme;
	}
	public String getEPrimaryV() {
		return ePrimaryV;
	}
	public void setEPrimaryV(String primaryV) {
		ePrimaryV = primaryV;
	}
	public String getEDirection() {
		return eDirection;
	}
	public void setEDirection(String direction) {
		eDirection = direction;
	}
	public String getEPrj_manager() {
		return ePrj_manager;
	}
	public void setEPrj_manager(String prj_manager) {
		ePrj_manager = prj_manager;
	}

	public String getEConstruction() {
		return eConstruction;
	}
	public void setEConstruction(String construction) {
		eConstruction = construction;
	}
	public String getETemp() {
		return eTemp;
	}
	public void setETemp(String temp) {
		eTemp = temp;
	}
	public String getEMode() {
		return eMode;
	}
	public void setEMode(String mode) {
		eMode = mode;
	}
	public String getEBrand() {
		return eBrand;
	}
	public void setEBrand(String brand) {
		eBrand = brand;
	}
	public String getEType() {
		return eType;
	}
	public void setEType(String type) {
		eType = type;
	}
	public String getEDiameter() {
		return eDiameter;
	}
	public void setEDiameter(String diameter) {
		eDiameter = diameter;
	}
	public String getESDR() {
		return eSDR;
	}
	public void setESDR(String esdr) {
		eSDR = esdr;
	}
	public String getERcode() {
		return eRcode;
	}
	public void setERcode(String rcode) {
		eRcode = rcode;
	}
	public String getERmeas() {
		return eRmeas;
	}
	public void setERmeas(String rmeas) {
		eRmeas = rmeas;
	}
	public String getEPos() {
		return ePos;
	}
	public void setEPos(String pos) {
		ePos = pos;
	}
	public String getEVolt() {
		return eVolt;
	}
	public void setEVolt(String volt) {
		eVolt = volt;
	}
	public String getEEnergie() {
		return eEnergie;
	}
	public void setEEnergie(String energie) {
		eEnergie = energie;
	}
	public String getETime() {
		return eTime;
	}
	public void setETime(String time) {
		eTime = time;
	}
	public String getENomdt() {
		return eNomdt;
	}
	public void setENomdt(String nomdt) {
		eNomdt = nomdt;
	}
	public String getEElas() {
		return eElas;
	}
	public void setEElas(String elas) {
		eElas = elas;
	}
	public String getERefresh() {
		return eRefresh;
	}
	public void setERefresh(String refresh) {
		eRefresh = refresh;
	}
	public String getEResult() {
		return eResult;
	}
	public void setEResult(String result) {
		eResult = result;
	}
	public String getECheckerAcceptor() {
		return eCheckerAcceptor;
	}
	public void setECheckerAcceptor(String checkerAcceptor) {
		if(checkerAcceptor.length()>=12){
			this.jianli = checkerAcceptor.substring(checkerAcceptor.length()-4,checkerAcceptor.length());
			this.zhijian = checkerAcceptor.substring(0,4);
		}
		eCheckerAcceptor = checkerAcceptor;
	}
	public String getEName() {
		return eName;
	}
	public void setEName(String name) {
		eName = name;
	}
	public String getEDiam1xDiam2() {
		return eDiam1xDiam2;
	}
	public void setEDiam1xDiam2(String diam1xDiam2) {
		eDiam1xDiam2 = diam1xDiam2;
	}
	public String getETp() {
		return eTp;
	}
	public void setETp(String tp) {
		eTp = tp;
	}
	public String getERegul() {
		return eRegul;
	}
	public void setERegul(String regul) {
		eRegul = regul;
	}
	public String getENom() {
		return eNom;
	}
	public void setENom(String nom) {
		eNom = nom;
	}
	public String getEAdju() {
		return eAdju;
	}
	public void setEAdju(String adju) {
		eAdju = adju;
	}
	public String getEP() {
		return eP;
	}
	public void setEP(String ep) {
		eP = ep;
	}
	public String getEEr() {
		return eEr;
	}
	public void setEEr(String er) {
		eEr = er;
	}
	public String getEManag() {
		return eManag;
	}
	public void setEManag(String manag) {
		eManag = manag;
	}
	
	public String geteProj() {
		return eProj;
	}
	public void seteProj(String eProj) {
		this.eProj = eProj;
	}
	public String getEProj() {
		return eProj;
	}
	public void setEProj(String eProj) {
		this.eProj = eProj;
	}
	public String getEOperat() {
		return eOperat;
	}
	public void setEOperat(String operat) {
		eOperat = operat;
	}
	public String getELocal() {
		return eLocal;
	}
	public void setELocal(String local) {
		eLocal = local;
	}
	public String getEInfo() {
		return eInfo;
	}
	public void setEInfo(String info) {
		eInfo = info;
	}
	public String getEFIId() {
		return eFIId;
	}
	public void setEFIId(String id) {
		eFIId = id;
	}
	public String getEfIversion() {
		return efIversion;
	}
	public void setEfIversion(String efIversion) {
		this.efIversion = efIversion;
	}
	
	
	
	
	@Override
	public String toString() {
		return "ElectrofusionData [eAdju=" + eAdju + ", eBrand=" + eBrand
				+ ", eCheckerAcceptor=" + eCheckerAcceptor + ", eCompany="
				+ eCompany + ", eConstruction=" + eConstruction + ", eDate="
				+ eDate + ", eDiam1xDiam2=" + eDiam1xDiam2 + ", eDiameter="
				+ eDiameter + ", eDirection=" + eDirection + ", eElas=" + eElas
				+ ", eEnergie=" + eEnergie + ", eEr=" + eEr + ", eFDId="
				+ eFDId + ", eFIId=" + eFIId + ", eHour=" + eHour + ", eInfo="
				+ eInfo + ", eLocal=" + eLocal + ", eManag=" + eManag
				+ ", eMode=" + eMode + ", eName=" + eName + ", eNo=" + eNo
				+ ", eNom=" + eNom + ", eNomdt=" + eNomdt + ", eNorme="
				+ eNorme + ", eOperat=" + eOperat + ", eP=" + eP + ", ePos="
				+ ePos + ", ePrimaryV=" + ePrimaryV + ", ePrj_manager="
				+ ePrj_manager + ", eProj=" + eProj + ", eRcode=" + eRcode
				+ ", eRefresh=" + eRefresh + ", eRegul=" + eRegul
				+ ", eResult=" + eResult + ", eRmeas=" + eRmeas + ", eSDR="
				+ eSDR + ", eTemp=" + eTemp + ", eTime=" + eTime + ", eTp="
				+ eTp + ", eType=" + eType + ", eVolt=" + eVolt
				+ ", efIversion=" + efIversion + "]";
	}
	
	
	
}
