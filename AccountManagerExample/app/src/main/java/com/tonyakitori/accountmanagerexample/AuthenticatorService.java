package com.tonyakitori.accountmanagerexample;

import android.accounts.AccountManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AuthenticatorService extends Service {

	private static AccountAuthenticator sAccountAuthenticator;
	
	@Override
	public IBinder onBind(Intent intent) {
		IBinder binder = null;
		if (intent.getAction().equals(AccountManager.ACTION_AUTHENTICATOR_INTENT)) {
			binder = getAuthenticator().getIBinder();
		}
		return binder;
	}
	
	private AccountAuthenticator getAuthenticator() {
		if (null == AuthenticatorService.sAccountAuthenticator) {
			AuthenticatorService.sAccountAuthenticator = new AccountAuthenticator(this);
		}
		return AuthenticatorService.sAccountAuthenticator;
	}

}