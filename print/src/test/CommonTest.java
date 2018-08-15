import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lubian.cpf.common.util.StringUtils;

@SuppressWarnings("unchecked")
public class CommonTest {

	@Before
	public void init() {
		
	}

	@After
	public void destory() {
		
	}

	public final void fail(String string) {
		System.out.println(string);
	}

	public final void failRed(String string) {
		System.err.println(string);
	}

	public void test() {
		String str = "123||sdf||";
		String[] obj =str.split("\\|");
		fail(obj.length+"");
		
		fail("balance=39987".substring(8));
	}	

	public void testA() throws UnsupportedEncodingException {
		int i = 300;
		String hexStr = Integer.toHexString(i);
		failRed(hexStr);
		byte[] b = StringUtils.hexString2Bytes("0"+hexStr);
		failRed(new String(b));
		String newHex = StringUtils.byte2hex(b);
		failRed(""+Integer.parseInt(newHex,16));
	}
	
	public byte[] getFixedLenghStr(int num) throws UnsupportedEncodingException{
		String hexStr = Integer.toHexString(num);
		//左补0
		int blankLen = 4 - hexStr.length();
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < blankLen; i++){
			buf.append("0");
		}
		String ret = buf.toString() + hexStr;
		return StringUtils.hexString2Bytes(ret);//把16进制每2位变成一个字节的字符
	}
	
	@Test
	public void testHex() throws UnsupportedEncodingException {
		//String hex = "60010600006031003199093232303130303032303030303030303030307C3032303030307C317C7C3130343131303135343131343636307C30313034373332317C3030303430317C32303134303930333133323231387C3030303030303030303030317C317C3435313238392A2A2A2A2A2A323131337C363745413131344545303839374435384644373042393935353538364432374532313537443943387C30307C30333039303031307CD0CBD2B5D2F8D0D07C3133323231383533303235387C303930337C3030303632327C";
		//String hex = "6001060000603100319909";
		int len = 300;
		String lenHex = Integer.toHexString(len);
		failRed(lenHex);
		//failRed(StringUtils.byte2hex(lenHex.getBytes()));
		byte[] fixed = getFixedLenghStr(len);
		String fixHex = StringUtils.byte2hex(fixed);
		failRed(fixHex);
		failRed(Integer.parseInt(fixHex, 16)+"");
	}
	
}
