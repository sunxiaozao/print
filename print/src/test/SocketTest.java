import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lubian.cpf.common.util.StringUtils;

@SuppressWarnings("unchecked")
public class SocketTest {

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
	public void testSocket() throws Exception {
		//String src = "0109"+"HABCD123456789012123456780123456789"
		//	+"|010020|0|9452287168570414|898110315200001|ZF230023|653412|20140214113900|";
		//String hex = "60010600006031003199093232303130303032303030303030303030307C3032303030307C317C7C3130343131303135343131343636307C30313034373332317C3030303430317C32303134303930333133323231387C3030303030303030303030317C317C3435313238392A2A2A2A2A2A323131337C363745413131344545303839374435384644373042393935353538364432374532313537443943387C30307C30333039303031307CD0CBD2B5D2F8D0D07C3133323231383533303235387C303930337C3030303632327C";
		String hex = "60010682c06031003199093232303130303032303030303030303030307C3032303032307C307C7C3130343131303135343131343636307C30313034373332317C3030303132347C32303134303930393130303731327C3030303030303030303030317C317C3435313238392A2A2A2A2A2A323131337C363745413131344545303839374435384644373042393935353538364432374532313537443943387C30307C30333039303031307CD0CBD2B5D2F8D0D07C3130303731323037373237317C303930397C3030303633307C303930397C3030303130317C3039343833343036303133387C7C";
		byte[] b = StringUtils.hexString2Bytes(hex);
		byte[] length = getFixedLenghStr(b.length);
		
		Socket s = new Socket("127.0.0.1", 6677);
		s.setSendBufferSize(4096);
		s.setTcpNoDelay(true);
		s.setSoTimeout(5000);
		s.setKeepAlive(true);
		OutputStream out = s.getOutputStream();
		InputStream in = s.getInputStream();
		// 准备报文src
		out.write(length);
		out.write(b);
		out.flush();
		
		int lengthHeadLen = 2;//报文头4个字节为报文总长度，不包括本身的4字节
		int contentLength = 0;//报文体长度			
		
		//获取报文长度字段
		byte[] lenHeadBuf = new byte[lengthHeadLen];
		int off = 0;
		while (off < lengthHeadLen) {
			off = off + in.read(lenHeadBuf, off, lengthHeadLen - off);
			if (off < 0) {
				failRed("读取POS报文头失败-->lenHeadBuf=" + new String(lenHeadBuf, "GBK"));
				throw new Exception("POS Socket was closed! while reading 4 head!");
			}
		}
		
		//获取报文头中的长度字段
		String hexStr = StringUtils.byte2hex(lenHeadBuf);
		contentLength = Integer.parseInt(hexStr,16);
		
		//获取报文体
		byte[] contentBuf = new byte[contentLength];
		off = 0;
		while (off < contentLength) {
			off = off + in.read(contentBuf, off, contentLength - off);
			if (off < 0) {
				failRed("读取POS报文体失败-->lenHeadBuf=" + new String(lenHeadBuf, "GBK"));
				throw new Exception("POS Socket was closed! while reading length-[" + contentLength	+ "] body.");
			}
		}
		
		byte[] header = new byte[30];
		System.arraycopy(contentBuf, 0, header, 0, 30);
		byte[] bodyByte = new byte[contentBuf.length - 30];
		System.arraycopy(contentBuf, 30, bodyByte, 0, bodyByte.length);
		String bodyStr = new String(bodyByte, "GBK");
		fail(StringUtils.byte2hex(header));
		fail(bodyStr);
	}	
	
}
