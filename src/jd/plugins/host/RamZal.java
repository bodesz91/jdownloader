//    jDownloader - Downloadmanager
//    Copyright (C) 2008  JD-Team jdownloader@freenet.de
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.


package jd.plugins.host;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import jd.plugins.DownloadLink;
import jd.plugins.HTTP;
import jd.plugins.HTTPConnection;
import jd.plugins.LinkStatus;
import jd.plugins.PluginForHost;

import jd.plugins.download.RAFDownload;import jd.plugins.LinkStatus;

public class RamZal extends PluginForHost {
	private static final String HOST = "ramzal.com";
	private static final String VERSION = "1.0.0.0";
	// http://ramzal.com//upload_files/1280838337_wallpaper-1280x1024-007.jpg
	static private final Pattern patternSupported = Pattern.compile(
			"http://[\\w\\.]*?ramzal\\.com//?upload_files/.*",
			Pattern.CASE_INSENSITIVE);

	//
	@Override
	public boolean doBotCheck(File file) {
		return false;
	} // kein BotCheck

	@Override
	public String getCoder() {
		return "JD-Team";
	}

	@Override
	public String getPluginName() {
		return HOST;
	}

	@Override
	public String getHost() {
		return HOST;
	}

	@Override
	public String getVersion() {
		return VERSION;
	}

	@Override
	public String getPluginID() {
		return HOST + "-" + VERSION;
	}

	@Override
	public Pattern getSupportedLinks() {
		return patternSupported;
	}

	public RamZal() {
		super();
		//steps.add(new PluginStep(PluginStep.STEP_DOWNLOAD, null));
	}

	public void handle(DownloadLink downloadLink) throws Exception{
	    
	    
	    LinkStatus linkStatus=downloadLink.getLinkStatus();
//		if (aborted) {
//			logger.warning("Plugin abgebrochen");
//			linkStatus.addStatus(LinkStatus.TODO);
//			//step.setStatus(PluginStep.STATUS_TODO);
//			return;
//		}
	
			requestInfo = HTTP.getRequestWithoutHtmlCode(new URL(downloadLink
					.getDownloadURL()), null, null, true);

			HTTPConnection urlConnection = requestInfo.getConnection();
			downloadLink.setName(getFileNameFormHeader(urlConnection));
			downloadLink.setDownloadMax(urlConnection.getContentLength());
		
		   dl = new RAFDownload(this, downloadLink, urlConnection);

           dl.startDownload();
           
//           \r\n if (!dl.startDownload() && step.getStatus() != PluginStep.STATUS_ERROR && step.getStatus() != PluginStep.STATUS_TODO) {
//                linkStatus.addStatus(LinkStatus.ERROR_RETRY);
//			
//				//step.setStatus(PluginStep.STATUS_ERROR);
//			}
//			return;
//
//		} catch (MalformedURLException e) {
//			
//			e.printStackTrace();
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		//step.setStatus(PluginStep.STATUS_ERROR);
//		linkStatus.addStatus(LinkStatus.ERROR_RETRY);
//		return;
	}

	@Override
	public boolean getFileInformation(DownloadLink downloadLink) { LinkStatus linkStatus=downloadLink.getLinkStatus();
		try {
			requestInfo = HTTP.getRequestWithoutHtmlCode(new URL(downloadLink
					.getDownloadURL()), null, null, true);
HTTPConnection urlConnection = requestInfo.getConnection();
			downloadLink.setName(getFileNameFormHeader(urlConnection));
			downloadLink.setDownloadMax(urlConnection.getContentLength());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public int getMaxSimultanDownloadNum() {
		return Integer.MAX_VALUE;
	}

	@Override
	public void reset() {
		// TODO Automatisch erstellter Methoden-Stub
	}

	@Override
	public void resetPluginGlobals() {
		// TODO Automatisch erstellter Methoden-Stub
	}

	@Override
	public String getAGBLink() {

		return "http://ramzal.com/";
	}
}
