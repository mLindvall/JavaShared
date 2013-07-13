package com.michaellindvall.net.proxy;

import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import com.btr.proxy.search.ProxySearch;
import com.btr.proxy.search.ProxySearch.Strategy;
import com.btr.proxy.util.PlatformUtil;
import com.btr.proxy.util.PlatformUtil.Platform;

/**
 * @author mlin014
 * 
 */
public class ProxyUtil {

  public static void setProxy(String url) throws URISyntaxException {
    ProxySearch proxySearch = new ProxySearch();

    if (PlatformUtil.getCurrentPlattform() == Platform.WIN) {
      proxySearch.addStrategy(Strategy.IE);
      proxySearch.addStrategy(Strategy.FIREFOX);
      proxySearch.addStrategy(Strategy.JAVA);
    }
    else if (PlatformUtil.getCurrentPlattform() == Platform.LINUX) {
      proxySearch.addStrategy(Strategy.GNOME);
      proxySearch.addStrategy(Strategy.KDE);
      proxySearch.addStrategy(Strategy.FIREFOX);
    }
    else {
      proxySearch.addStrategy(Strategy.OS_DEFAULT);
    }

    ProxySelector myProxySelector = proxySearch.getProxySelector();

    ProxySelector.setDefault(myProxySelector);

    List<Proxy> proxies;

    proxies = myProxySelector.select(new URI(url));
    log(proxies.size() + " proxies found via ProxySelector " + myProxySelector.getClass());
    for (Proxy proxy : proxies) {
      log(proxy.toString());
      log(PlatformUtil.getCurrentPlattform().toString());
    }
  }
  
  private static void log(String s) {
    System.out.println("[" + ProxyUtil.class.getSimpleName() + ": " + s + " ]");
  }
}
