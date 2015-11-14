package xqfcounter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 读取XQF文件
 *
 * @param
 * @author shifeng
 */
public class XQFLoader {

    File file;
    byte[] XQFLabel;
    byte[] version;
    byte[] kaiju;
    byte[] result;
    byte[] classify;
    byte[] namelength;
    byte[] name;
    byte[] matchnamelength;
    byte[] matchname;
    byte[] matchdatelength;
    byte[] matchdate;
    byte[] matchplacelength;
    byte[] matchplace;
    byte[] rednamelength;
    byte[] redname;
    byte[] blacknamelength;
    byte[] blackname;
    byte[] timerulelength;
    byte[] timerule;
    byte[] redtimelength;
    byte[] redtime;
    byte[] blacktimelength;
    byte[] blacktime;
    byte[] docentlength;
    byte[] docent;
    byte[] fileauthorlength;
    byte[] fileauthor;

    XQFLoader(File file) {
        this.file = file;
        this.XQFLabel = new byte[2];
        this.version = new byte[1];
        this.kaiju = new byte[32];
        this.result = new byte[1];
        this.classify = new byte[1];
        this.namelength = new byte[1];
        this.name = new byte[0x3f];
        this.matchnamelength = new byte[1];
        this.matchname = new byte[0x3f];
        this.matchdatelength = new byte[1];
        this.matchdate = new byte[15];
        this.matchplacelength = new byte[1];
        this.matchplace = new byte[15];
        this.rednamelength = new byte[1];
        this.redname = new byte[15];
        this.blacknamelength = new byte[1];
        this.blackname = new byte[15];
        this.timerulelength = new byte[1];
        this.timerule = new byte[0x3f];
        this.redtimelength = new byte[1];
        this.redtime = new byte[15];
        this.blacktimelength = new byte[1];
        this.blacktime = new byte[15];
        this.docentlength = new byte[1];
        this.docent = new byte[15];
        this.fileauthorlength = new byte[1];
        this.fileauthor = new byte[15];
    }

    String byte2string(byte[] bytes,byte[] lengthbyte) {
        int length = lengthbyte[0];
        byte[] realbytes = new byte[length];
        for(int i = 0;i<length;++i){
            realbytes[i]=bytes[i];
        }
        try {
            return new String(realbytes, "GB2312");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(XQFLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    void load() throws FileNotFoundException, Exception {
        try {
            FileInputStream fis;
            fis = new FileInputStream(file);
            if (fis.available() > 0) {
                fis.read(XQFLabel);
                byte[] XQFLabellenth= new byte[1];
                XQFLabellenth[0]=2;
        if(!byte2string(XQFLabel,XQFLabellenth).equals("XQ")){
         fis.close();
            throw new Exception("NOTXQ");
        }               
                
                fis.read(version);
                fis.skip(0xf - 3 + 1);
                fis.read(kaiju);
                fis.skip(3);
                fis.read(result);
                fis.skip(0x3f - 0x34 + 1);
                fis.read(classify);
                fis.skip(0x4f - 0x41 + 1);
                fis.read(namelength);
                fis.read(name);
                fis.skip(0xcf - 0x90 + 1);
                fis.read(matchnamelength);
                fis.read(matchname);
                fis.read(matchdatelength);
                fis.read(matchdate);
                fis.read(matchplacelength);
                fis.read(matchplace);
                fis.read(rednamelength);
                fis.read(redname);
                fis.read(blacknamelength);
                fis.read(blackname);
                fis.read(timerulelength);
                fis.read(timerule);
                fis.read(redtimelength);
                fis.read(redtime);
                fis.read(blacktimelength);
                fis.read(blacktime);
                fis.skip(0x1cf - 0x1b0 + 1);
                fis.read(docentlength);
                fis.read(docent);
                fis.read(fileauthorlength);
                fis.read(fileauthor);
                fis.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(XQFLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String countkaiju() {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (kaiju[0] != -1) {
            ++count;
        }
        if (kaiju[8] != -1) {
            ++count;
        }
        sb.append(count);
        count = 0;
        if (kaiju[1] != -1) {
            ++count;
        }
        if (kaiju[7] != -1) {
            ++count;
        }
        sb.append(count);
        count = 0;
        if (kaiju[9] != -1) {
            ++count;
        }
        if (kaiju[10] != -1) {
            ++count;
        }
        sb.append(count);
        count = 0;
        if (kaiju[11] != -1) {
            ++count;
        }
        if (kaiju[12] != -1) {
            ++count;
        }
        if (kaiju[13] != -1) {
            ++count;
        }
        if (kaiju[14] != -1) {
            ++count;
        }
        if (kaiju[15] != -1) {
            ++count;
        }
        sb.append(count);
        count = 0;
        sb.append(".");
        if (kaiju[3] != -1) {
            ++count;
        }
        if (kaiju[5] != -1) {
            ++count;
        }
        sb.append(count);
        count = 0;
        if (kaiju[2] != -1) {
            ++count;
        }
        if (kaiju[6] != -1) {
            ++count;
        }
        sb.append(count);
        count = 0;
        sb.append("-");
        if (kaiju[0 + 16] != -1) {
            ++count;
        }
        if (kaiju[8 + 16] != -1) {
            ++count;
        }
        sb.append(count);
        count = 0;
        if (kaiju[1 + 16] != -1) {
            ++count;
        }
        if (kaiju[7 + 16] != -1) {
            ++count;
        }
        sb.append(count);
        count = 0;
        if (kaiju[9 + 16] != -1) {
            ++count;
        }
        if (kaiju[10 + 16] != -1) {
            ++count;
        }
        sb.append(count);
        count = 0;
        if (kaiju[11 + 16] != -1) {
            ++count;
        }
        if (kaiju[12 + 16] != -1) {
            ++count;
        }
        if (kaiju[13 + 16] != -1) {
            ++count;
        }
        if (kaiju[14 + 16] != -1) {
            ++count;
        }
        if (kaiju[15 + 16] != -1) {
            ++count;
        }
        sb.append(count);
        count = 0;
        sb.append(".");
        if (kaiju[3 + 16] != -1) {
            ++count;
        }
        if (kaiju[5 + 16] != -1) {
            ++count;
        }
        sb.append(count);
        count = 0;
        if (kaiju[2 + 16] != -1) {
            ++count;
        }
        if (kaiju[6 + 16] != -1) {
            ++count;
        }
        sb.append(count);
        return sb.toString();
    }
    
    private String formatkaiju() {
        StringBuilder sb = new StringBuilder();
        sb.append(kaiju[0]).append("|");
        sb.append(kaiju[8]).append("|");
        sb.append(kaiju[1]).append("|");
        sb.append(kaiju[7]).append("|");
        sb.append(kaiju[9]).append("|");
        sb.append(kaiju[10]).append("|");
        sb.append(kaiju[11]).append("|");
        sb.append(kaiju[12]).append("|");
        sb.append(kaiju[13]).append("|");
        sb.append(kaiju[14]).append("|");
        sb.append(kaiju[15]);
        sb.append(".");
        sb.append(kaiju[3]).append("|");
        sb.append(kaiju[5]).append("|");
        sb.append(kaiju[2]).append("|");
        sb.append(kaiju[6]);
        sb.append("-");
        sb.append(kaiju[0 + 16]).append("|");
        sb.append(kaiju[8 + 16]).append("|");
        sb.append(kaiju[1 + 16]).append("|");
        sb.append(kaiju[7 + 16]).append("|");
        sb.append(kaiju[9 + 16]).append("|");
        sb.append(kaiju[10 + 16]).append("|");
        sb.append(kaiju[11 + 16]).append("|");
        sb.append(kaiju[12 + 16]).append("|");
        sb.append(kaiju[13 + 16]).append("|");
        sb.append(kaiju[14 + 16]).append("|");
        sb.append(kaiju[15 + 16]);
        sb.append(".");
        sb.append(kaiju[3 + 16]).append("|");
        sb.append(kaiju[5 + 16]).append("|");
        sb.append(kaiju[2 + 16]).append("|");
        sb.append(kaiju[6 + 16]);
        
        return sb.toString();
    }

    private String formatresult() {
        if (result[0] == 0x1) {
            return "红胜";
        } else if (result[0] == 0x2) {
            return "黑胜";
        } else if (result[0] == 0x3) {
            return "和棋";
        } else {
            return "未知胜负";
        }

    }

    private String formatclassify() {
        if (classify[0] == 0x0) {
            return "全局文件";
        } else if (classify[0] == 0x1) {
            return "布局文件";
        } else if (classify[0] == 0x2) {
            return "中局文件";
        } else if (classify[0] == 0x3) {
            return "残局文件";
        } else {
            return "错误的棋局类型";
        }
    }

    private String formatname() {
        String byte2string = byte2string(name,namelength);
        return byte2string;
    }

    private String formatmatchname() {
        String byte2string = byte2string(matchname,matchnamelength);
         return byte2string;
    }

    private String formatmatchdate() {
        String byte2string = byte2string(matchdate,matchdatelength);
        return byte2string;
    }

    private String formatmatchplace() {
        String byte2string = byte2string(matchplace,matchplacelength);
        return byte2string;
    }

    private String formatredname() {
        String byte2string = byte2string(redname,rednamelength);
        return byte2string;
    }

    private String formatblackname() {
        String byte2string = byte2string(blackname,blacknamelength);
        return byte2string;
    }

    private String formattimerule() {
        String byte2string = byte2string(timerule,timerulelength);
        return byte2string;
    }

    private String formatredtime() {
        String byte2string = byte2string(redtime,redtimelength);
        return byte2string;
    }

    private String formatblacktime() {
        String byte2string = byte2string(blacktime,blacktimelength);
        return byte2string;
    }

    private String formatdocent() {
        String byte2string = byte2string(docent,docentlength);
        return byte2string;
    }

    private String formatfileauthor() {
        String byte2string = byte2string(fileauthor,fileauthorlength);
        return byte2string;
    }

    public List<String> format() {
        List<String> list = new LinkedList<String>();
        byte[] XQFLabellenth = new byte[1];
        XQFLabellenth[0]=2;
        list.add(byte2string(XQFLabel,XQFLabellenth));
        Float floatVer = new Float(version[0]);
        floatVer /= 10;
        list.add(floatVer.toString());
        list.add(countkaiju());
        list.add(formatkaiju());
        list.add(Integer.toString(formatkaiju().hashCode()));
        list.add(formatresult());
        list.add(formatclassify());
        list.add(formatname());
        list.add(formatmatchname());
        list.add(formatmatchdate());
        list.add(formatmatchplace());
        list.add(formatredname());
        list.add(formatblackname());
        list.add(formattimerule());
        list.add(formatredtime());
        list.add(formatblacktime());
        list.add(formatdocent());
        list.add(formatfileauthor());
        return list;

    }

    @Override
    public String toString() {
        StringBuilder sb;
        sb = new StringBuilder();
        sb.append("XQFLoader{" + "file=" + file + ", kaiju=");
        for (byte b : kaiju) {
            int t = (int) b;
            sb.append(t);
            sb.append(",");
        }
        sb.append("}");
        return sb.toString();
    }
}
