package com.xatu.yuexin.welding4sl.vo;


/**
 * 热熔具体数据信息
 * @author Administrator
 *
 */
public class HotmeltData  {

	
	private int hId;
	private String hNo;    
	private String hDate;    
	private String hHour;    
	private String hEnd;    
	private String hOperat;    
	private String hWay;    
	private String hInfo;    
	private String hManagement;    
	private String hProject;    
	private String hConstruction_code;  
	private String hMode;    
	private String hNorme;    
	private String hTemp;    
	private String hPE;    
	private String hDiam;    
	private String hThick;    
	private String hPipe1;    
	private String hType1;    
	private String hBrand1;    
	private String hPE11;    
	private String hMateri1;    
	private String hMFR1;    
	private String hInfo_11;    
	private String hInfo_21;    
	private String hPipe2;    
	private String hType2;    
	private String hBrand2;    
	private String hPE22;    
	private String hMateri2;    
	private String hMFR2;    
	private String hInfo_12;    
	private String hInfo_22;    
	private String hTHE;    
	private String hDrg;    
	private String hPb;    
	private String hTb;    
	private String hTbRe;    
	private String hPs;    
	private String hTs;    
	private String hTsRe;    
	private String hTej;    
	private String hTup;    
	private String hPf;    
	private String hTf;    
	private String hTfRe;    
	private String hPf2;    
	private String hTf2;    
	private String hTf2Re;    
	private String hPCo;    
	private String hTCo;    
	private String hTrCo;    
	private String hPmB;    
	private String hPmS;    
	private String hPmF;    
	private String hPmC;   
	private String hEr;    
	private String hMIId;
	
	private String HGPS_UTC;  
	private String HGPS_latitude;    
	private String HGPS_longitude;   
	private String HGPS_status;    
	private String HGPS_high;
	
	private String hcompany;
	private String zhijian;
	private String jianli;
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
	public String getHcompany() {
		return hcompany;
	}
	public void setHcompany(String hcompany) {
		this.hcompany = hcompany;
	}
	public String getZhijian() {
		return zhijian;
	}
	public void setZhijian(String zhijian) {
		this.zhijian = zhijian;
	}
	public String getHGPS_UTC() {
		return HGPS_UTC;
	}
	public void setHGPS_UTC(String hGPSUTC) {
		HGPS_UTC = hGPSUTC;
	}
	public String getHGPS_latitude() {
		return HGPS_latitude;
	}
	public void setHGPS_latitude(String hGPSLatitude) {
		HGPS_latitude = hGPSLatitude;
	}
	public String getHGPS_longitude() {
		return HGPS_longitude;
	}
	public void setHGPS_longitude(String hGPSLongitude) {
		HGPS_longitude = hGPSLongitude;
	}
	public String getHGPS_status() {
		return HGPS_status;
	}
	public void setHGPS_status(String hGPSStatus) {
		HGPS_status = hGPSStatus;
	}
	public String getHGPS_high() {
		return HGPS_high;
	}
	public void setHGPS_high(String hGPSHigh) {
		HGPS_high = hGPSHigh;
	}

	public int getHId() {
		return hId;
	}
	public void setHId(int id) {
		hId = id;
	}
	public String getHNo() {
		return hNo;
	}
	public void setHNo(String no) {
		hNo = no;
	}
	public String getHDate() {
		return hDate;
	}
	public void setHDate(String date) {
		hDate = date;
	}
	public String getHHour() {
		return hHour;
	}
	public void setHHour(String hour) {
		hHour = hour;
	}
	public String getHEnd() {
		return hEnd;
	}
	public void setHEnd(String end) {
		hEnd = end;
	}
	public String getHOperat() {
		return hOperat;
	}
	public void setHOperat(String operat) {
		hOperat = operat;
	}
	public String getHWay() {
		return hWay;
	}
	public void setHWay(String way) {
		hWay = way;
	}
	public String getHInfo() {
		return hInfo;
	}
	public void setHInfo(String info) {
		if(info.length()>=16){
			this.zhijian = info.substring(info.length()-4,info.length());
			this.jianli = info.substring(info.length()-8,info.length()-4);
			hInfo = info.substring(0,info.length()-8);
		}else{
			hInfo = info;
		}
		
	}
	public String getHManagement() {
		return hManagement;
	}
	public void setHManagement(String management) {
		hManagement = management;
	}
	public String getHProject() {
		return hProject;
	}
	public void setHProject(String project) {
		hProject = project;
	}
	public String getHConstruction_code() {
		return hConstruction_code;
	}
	public void setHConstruction_code(String construction_code) {
		hConstruction_code = construction_code;
	}
	public String getHMode() {
		return hMode;
	}
	public void setHMode(String mode) {
		hMode = mode;
	}
	public String getHNorme() {
		return hNorme;
	}
	public void setHNorme(String norme) {
		hNorme = norme;
	}
	public String getHTemp() {
		return hTemp;
	}
	public void setHTemp(String temp) {
		hTemp = temp;
	}
	public String getHPE() {
		return hPE;
	}
	public void setHPE(String hpe) {
		hPE = hpe;
	}
	public String getHDiam() {
		return hDiam;
	}
	public void setHDiam(String diam) {
		hDiam = diam;
	}
	public String getHThick() {
		return hThick;
	}
	public void setHThick(String thick) {
		hThick = thick;
	}
	public String getHPipe1() {
		return hPipe1;
	}
	public void setHPipe1(String pipe1) {
		hPipe1 = pipe1;
	}
	public String getHType1() {
		return hType1;
	}
	public void setHType1(String type1) {
		hType1 = type1;
	}
	public String getHBrand1() {
		return hBrand1;
	}
	public void setHBrand1(String brand1) {
		hBrand1 = brand1;
	}
	public String getHPE11() {
		return hPE11;
	}
	public void setHPE11(String hpe11) {
		hPE11 = hpe11;
	}
	public String getHMateri1() {
		return hMateri1;
	}
	public void setHMateri1(String materi1) {
		hMateri1 = materi1;
	}
	public String getHMFR1() {
		return hMFR1;
	}
	public void setHMFR1(String hmfr1) {
		hMFR1 = hmfr1;
	}
	public String getHInfo_11() {
		return hInfo_11;
	}
	public void setHInfo_11(String info_11) {
		hInfo_11 = info_11;
	}
	public String getHInfo_21() {
		return hInfo_21;
	}
	public void setHInfo_21(String info_21) {
		hInfo_21 = info_21;
	}
	public String getHPipe2() {
		return hPipe2;
	}
	public void setHPipe2(String pipe2) {
		hPipe2 = pipe2;
	}
	public String getHType2() {
		return hType2;
	}
	public void setHType2(String type2) {
		hType2 = type2;
	}
	public String getHBrand2() {
		return hBrand2;
	}
	public void setHBrand2(String brand2) {
		hBrand2 = brand2;
	}
	public String getHPE22() {
		return hPE22;
	}
	public void setHPE22(String hpe22) {
		hPE22 = hpe22;
	}
	public String getHMateri2() {
		return hMateri2;
	}
	public void setHMateri2(String materi2) {
		hMateri2 = materi2;
	}
	public String getHMFR2() {
		return hMFR2;
	}
	public void setHMFR2(String hmfr2) {
		hMFR2 = hmfr2;
	}
	public String getHInfo_12() {
		return hInfo_12;
	}
	public void setHInfo_12(String info_12) {
		hInfo_12 = info_12;
	}
	public String getHInfo_22() {
		return hInfo_22;
	}
	public void setHInfo_22(String info_22) {
		hInfo_22 = info_22;
	}
	public String getHTHE() {
		return hTHE;
	}
	public void setHTHE(String hthe) {
		hTHE = hthe;
	}
	public String getHDrg() {
		return hDrg;
	}
	public void setHDrg(String drg) {
		hDrg = drg;
	}
	public String getHPb() {
		return hPb;
	}
	public void setHPb(String pb) {
		hPb = pb;
	}
	public String getHTb() {
		return hTb;
	}
	public void setHTb(String tb) {
		hTb = tb;
	}
	public String getHTbRe() {
		return hTbRe;
	}
	public void setHTbRe(String tbRe) {
		hTbRe = tbRe;
	}
	public String getHPs() {
		return hPs;
	}
	public void setHPs(String ps) {
		hPs = ps;
	}
	public String getHTs() {
		return hTs;
	}
	public void setHTs(String ts) {
		hTs = ts;
	}
	public String getHTsRe() {
		return hTsRe;
	}
	public void setHTsRe(String tsRe) {
		hTsRe = tsRe;
	}
	public String getHTej() {
		return hTej;
	}
	public void setHTej(String tej) {
		hTej = tej;
	}
	public String getHTup() {
		return hTup;
	}
	public void setHTup(String tup) {
		hTup = tup;
	}
	public String getHPf() {
		return hPf;
	}
	public void setHPf(String pf) {
		hPf = pf;
	}
	public String getHTf() {
		return hTf;
	}
	public void setHTf(String tf) {
		hTf = tf;
	}
	public String getHTfRe() {
		return hTfRe;
	}
	public void setHTfRe(String tfRe) {
		hTfRe = tfRe;
	}
	public String getHPf2() {
		return hPf2;
	}
	public void setHPf2(String pf2) {
		hPf2 = pf2;
	}
	public String getHTf2() {
		return hTf2;
	}
	public void setHTf2(String tf2) {
		hTf2 = tf2;
	}
	public String getHTf2Re() {
		return hTf2Re;
	}
	public void setHTf2Re(String tf2Re) {
		hTf2Re = tf2Re;
	}
	public String getHPCo() {
		return hPCo;
	}
	public void setHPCo(String co) {
		hPCo = co;
	}
	public String getHTCo() {
		return hTCo;
	}
	public void setHTCo(String co) {
		hTCo = co;
	}
	public String getHTrCo() {
		return hTrCo;
	}
	public void setHTrCo(String trCo) {
		hTrCo = trCo;
	}
	public String getHPmB() {
		return hPmB;
	}
	public void setHPmB(String pmB) {
		hPmB = pmB;
	}
	public String getHPmS() {
		return hPmS;
	}
	public void setHPmS(String pmS) {
		hPmS = pmS;
	}
	public String getHPmF() {
		return hPmF;
	}
	public void setHPmF(String pmF) {
		hPmF = pmF;
	}
	public String getHPmC() {
		return hPmC;
	}
	public void setHPmC(String pmC) {
		hPmC = pmC;
	}
	public String getHEr() {
		return hEr;
	}
	public void setHEr(String er) {
		hEr = er;
	}
	public String getHMIId() {
		return hMIId;
	}
	public void setHMIId(String id) {
		hMIId = id;
	}
	
	public int gethId() {
		return hId;
	}
	public void sethId(int hId) {
		this.hId = hId;
	}
	public String gethNo() {
		return hNo;
	}
	public void sethNo(String hNo) {
		this.hNo = hNo;
	}
	public String gethDate() {
		return hDate;
	}
	public void sethDate(String hDate) {
		this.hDate = hDate;
	}
	public String gethHour() {
		return hHour;
	}
	public void sethHour(String hHour) {
		this.hHour = hHour;
	}
	public String gethEnd() {
		return hEnd;
	}
	public void sethEnd(String hEnd) {
		this.hEnd = hEnd;
	}
	public String gethOperat() {
		return hOperat;
	}
	public void sethOperat(String hOperat) {
		this.hOperat = hOperat;
	}
	public String gethWay() {
		return hWay;
	}
	public void sethWay(String hWay) {
		this.hWay = hWay;
	}
	public String gethInfo() {
		return hInfo;
	}
	public void sethInfo(String hInfo) {
		this.hInfo = hInfo;
	}
	public String gethManagement() {
		return hManagement;
	}
	public void sethManagement(String hManagement) {
		this.hManagement = hManagement;
	}
	public String gethProject() {
		return hProject;
	}
	public void sethProject(String hProject) {
		this.hProject = hProject;
	}
	public String gethConstruction_code() {
		return hConstruction_code;
	}
	public void sethConstruction_code(String hConstructionCode) {
		hConstruction_code = hConstructionCode;
	}
	public String gethMode() {
		return hMode;
	}
	public void sethMode(String hMode) {
		this.hMode = hMode;
	}
	public String gethNorme() {
		return hNorme;
	}
	public void sethNorme(String hNorme) {
		this.hNorme = hNorme;
	}
	public String gethTemp() {
		return hTemp;
	}
	public void sethTemp(String hTemp) {
		this.hTemp = hTemp;
	}
	public String gethPE() {
		return hPE;
	}
	public void sethPE(String hPE) {
		this.hPE = hPE;
	}
	public String gethDiam() {
		return hDiam;
	}
	public void sethDiam(String hDiam) {
		this.hDiam = hDiam;
	}
	public String gethThick() {
		return hThick;
	}
	public void sethThick(String hThick) {
		this.hThick = hThick;
	}
	public String gethPipe1() {
		return hPipe1;
	}
	public void sethPipe1(String hPipe1) {
		this.hPipe1 = hPipe1;
	}
	public String gethType1() {
		return hType1;
	}
	public void sethType1(String hType1) {
		this.hType1 = hType1;
	}
	public String gethBrand1() {
		return hBrand1;
	}
	public void sethBrand1(String hBrand1) {
		this.hBrand1 = hBrand1;
	}
	public String gethPE11() {
		return hPE11;
	}
	public void sethPE11(String hPE11) {
		this.hPE11 = hPE11;
	}
	public String gethMateri1() {
		return hMateri1;
	}
	public void sethMateri1(String hMateri1) {
		this.hMateri1 = hMateri1;
	}
	public String gethMFR1() {
		return hMFR1;
	}
	public void sethMFR1(String hMFR1) {
		this.hMFR1 = hMFR1;
	}
	public String gethInfo_11() {
		return hInfo_11;
	}
	public void sethInfo_11(String hInfo_11) {
		this.hInfo_11 = hInfo_11;
	}
	public String gethInfo_21() {
		return hInfo_21;
	}
	public void sethInfo_21(String hInfo_21) {
		this.hInfo_21 = hInfo_21;
	}
	public String gethPipe2() {
		return hPipe2;
	}
	public void sethPipe2(String hPipe2) {
		this.hPipe2 = hPipe2;
	}
	public String gethType2() {
		return hType2;
	}
	public void sethType2(String hType2) {
		this.hType2 = hType2;
	}
	public String gethBrand2() {
		return hBrand2;
	}
	public void sethBrand2(String hBrand2) {
		this.hBrand2 = hBrand2;
	}
	public String gethPE22() {
		return hPE22;
	}
	public void sethPE22(String hPE22) {
		this.hPE22 = hPE22;
	}
	public String gethMateri2() {
		return hMateri2;
	}
	public void sethMateri2(String hMateri2) {
		this.hMateri2 = hMateri2;
	}
	public String gethMFR2() {
		return hMFR2;
	}
	public void sethMFR2(String hMFR2) {
		this.hMFR2 = hMFR2;
	}
	public String gethInfo_12() {
		return hInfo_12;
	}
	public void sethInfo_12(String hInfo_12) {
		this.hInfo_12 = hInfo_12;
	}
	public String gethInfo_22() {
		return hInfo_22;
	}
	public void sethInfo_22(String hInfo_22) {
		this.hInfo_22 = hInfo_22;
	}
	public String gethTHE() {
		return hTHE;
	}
	public void sethTHE(String hTHE) {
		this.hTHE = hTHE;
	}
	public String gethDrg() {
		return hDrg;
	}
	public void sethDrg(String hDrg) {
		this.hDrg = hDrg;
	}
	public String gethPb() {
		return hPb;
	}
	public void sethPb(String hPb) {
		this.hPb = hPb;
	}
	public String gethTb() {
		return hTb;
	}
	public void sethTb(String hTb) {
		this.hTb = hTb;
	}
	public String gethTbRe() {
		return hTbRe;
	}
	public void sethTbRe(String hTbRe) {
		this.hTbRe = hTbRe;
	}
	public String gethPs() {
		return hPs;
	}
	public void sethPs(String hPs) {
		this.hPs = hPs;
	}
	public String gethTs() {
		return hTs;
	}
	public void sethTs(String hTs) {
		this.hTs = hTs;
	}
	public String gethTsRe() {
		return hTsRe;
	}
	public void sethTsRe(String hTsRe) {
		this.hTsRe = hTsRe;
	}
	public String gethTej() {
		return hTej;
	}
	public void sethTej(String hTej) {
		this.hTej = hTej;
	}
	public String gethTup() {
		return hTup;
	}
	public void sethTup(String hTup) {
		this.hTup = hTup;
	}
	public String gethPf() {
		return hPf;
	}
	public void sethPf(String hPf) {
		this.hPf = hPf;
	}
	public String gethTf() {
		return hTf;
	}
	public void sethTf(String hTf) {
		this.hTf = hTf;
	}
	public String gethTfRe() {
		return hTfRe;
	}
	public void sethTfRe(String hTfRe) {
		this.hTfRe = hTfRe;
	}
	public String gethPf2() {
		return hPf2;
	}
	public void sethPf2(String hPf2) {
		this.hPf2 = hPf2;
	}
	public String gethTf2() {
		return hTf2;
	}
	public void sethTf2(String hTf2) {
		this.hTf2 = hTf2;
	}
	public String gethTf2Re() {
		return hTf2Re;
	}
	public void sethTf2Re(String hTf2Re) {
		this.hTf2Re = hTf2Re;
	}
	public String gethPCo() {
		return hPCo;
	}
	public void sethPCo(String hPCo) {
		this.hPCo = hPCo;
	}
	public String gethTCo() {
		return hTCo;
	}
	public void sethTCo(String hTCo) {
		this.hTCo = hTCo;
	}
	public String gethTrCo() {
		return hTrCo;
	}
	public void sethTrCo(String hTrCo) {
		this.hTrCo = hTrCo;
	}
	public String gethPmB() {
		return hPmB;
	}
	public void sethPmB(String hPmB) {
		this.hPmB = hPmB;
	}
	public String gethPmS() {
		return hPmS;
	}
	public void sethPmS(String hPmS) {
		this.hPmS = hPmS;
	}
	public String gethPmF() {
		return hPmF;
	}
	public void sethPmF(String hPmF) {
		this.hPmF = hPmF;
	}
	public String gethPmC() {
		return hPmC;
	}
	public void sethPmC(String hPmC) {
		this.hPmC = hPmC;
	}
	public String gethEr() {
		return hEr;
	}
	public void sethEr(String hEr) {
		this.hEr = hEr;
	}
	public String gethMIId() {
		return hMIId;
	}
	public void sethMIId(String hMIId) {
		this.hMIId = hMIId;
	}
	@Override
	public String toString() {
		return "HotmeltData [HGPS_UTC=" + HGPS_UTC + ", HGPS_high=" + HGPS_high
				+ ", HGPS_latitude=" + HGPS_latitude + ", HGPS_longitude="
				+ HGPS_longitude + ", HGPS_status=" + HGPS_status
				+ ", hBrand1=" + hBrand1 + ", hBrand2=" + hBrand2
				+ ", hConstruction_code=" + hConstruction_code + ", hDate="
				+ hDate + ", hDiam=" + hDiam + ", hDrg=" + hDrg + ", hEnd="
				+ hEnd + ", hEr=" + hEr + ", hHour=" + hHour + ", hId=" + hId
				+ ", hInfo=" + hInfo + ", hInfo_11=" + hInfo_11 + ", hInfo_12="
				+ hInfo_12 + ", hInfo_21=" + hInfo_21 + ", hInfo_22="
				+ hInfo_22 + ", hMFR1=" + hMFR1 + ", hMFR2=" + hMFR2
				+ ", hMIId=" + hMIId + ", hManagement=" + hManagement
				+ ", hMateri1=" + hMateri1 + ", hMateri2=" + hMateri2
				+ ", hMode=" + hMode + ", hNo=" + hNo + ", hNorme=" + hNorme
				+ ", hOperat=" + hOperat + ", hPCo=" + hPCo + ", hPE=" + hPE
				+ ", hPE11=" + hPE11 + ", hPE22=" + hPE22 + ", hPb=" + hPb
				+ ", hPf=" + hPf + ", hPf2=" + hPf2 + ", hPipe1=" + hPipe1
				+ ", hPipe2=" + hPipe2 + ", hPmB=" + hPmB + ", hPmC=" + hPmC
				+ ", hPmF=" + hPmF + ", hPmS=" + hPmS + ", hProject="
				+ hProject + ", hPs=" + hPs + ", hTCo=" + hTCo + ", hTHE="
				+ hTHE + ", hTb=" + hTb + ", hTbRe=" + hTbRe + ", hTej=" + hTej
				+ ", hTemp=" + hTemp + ", hTf=" + hTf + ", hTf2=" + hTf2
				+ ", hTf2Re=" + hTf2Re + ", hTfRe=" + hTfRe + ", hThick="
				+ hThick + ", hTrCo=" + hTrCo + ", hTs=" + hTs + ", hTsRe="
				+ hTsRe + ", hTup=" + hTup + ", hType1=" + hType1 + ", hType2="
				+ hType2 + ", hWay=" + hWay + "]";
	}
	
	
	
	
	
}
