package com.test.wav;

public class VoiceTransformUtil {

	public static final String TYPE_SOX = "sox";
	public static final String TYPE_LAME = "lame";
	
	public int voiceTransform(String type,String fileName,String transformName){
		Runtime run = Runtime.getRuntime();//�����뵱ǰ Java Ӧ�ó�����ص�����ʱ����  
		try {  
			String cmd = createCmd(type,fileName,transformName);
			System.out.println(cmd);
            Process p = run.exec(cmd);// ������һ��������ִ������  
            if (p.waitFor() != 0) {
            	return p.exitValue();
            }else{
            	Process rp = run.exec("rm "+ fileName);// ������һ��������ִ������  
            	if(rp.waitFor() != 0){
            		return rp.exitValue();
            	}
            }
        } catch (Exception e) {  
            e.printStackTrace();
            return -1;
        }  
        return 0;
	}
	
	
	private String createCmd(String type,String fileName,String transformName){
		StringBuffer sb = new StringBuffer();
		if(TYPE_LAME.equals(type)){
			sb.append("lame -V2 " + fileName + " " + transformName);
		}else{
			sb.append("sox " + fileName + " -r 44100 -c 2 " + transformName);
		}
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		VoiceTransformUtil u = new VoiceTransformUtil();
		System.out.println("start");
		String fileName = "/local/assets/mxkimage/t.wav";
		String transformName = "/local/assets/mxkimage/liuyij.mp3";
		int i = u.voiceTransform(VoiceTransformUtil.TYPE_LAME, fileName, transformName);
		System.out.println(i);
	}

}
