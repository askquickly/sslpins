## SSL Pinning

AndroidPinning is a standalone Android B4A library project that facilitates certificate pinning for SSL connections from Android apps, in order to minimize dependence on Certificate Authorities.

CA signatures are necessary for general purpose network communication tools: things like web browsers, which connect to arbitrary network endpoints and have no advance knowledge of what the SSL certificates for those endpoint should look like.

Most mobile apps are not general purpose communication tools. Instead, they typically connect directly to a narrow set of backend services that the app's author either controls, or can predict ahead of time.

This creates an opportunity for app developers to sidestep the security problems inherent with Certificate Authorities. The best way is to throw CA certificates out the window entirely by signing your own endpoint certificates with your own offline signing certificate, which you then distribute with your app. See this blog post for examples of the no-CA technique.

Sometimes, however, that's not possible, and you need to continue using CA certificates for one reason or another. Perhaps the API endpoint is shared with a web browser's endpoint, for instance.

In that case, it's necessary to employ "pinning," which is simply the act of verifying that the certificate chain looks the way you know it should, even if it's signed by a CA. This prevents other CAs from being able to effectively create forged certificates for your domain, as with the many Comodo breaches, the DigiNotar breach, and the TurkTrust breach.

## Usage

```
requester As sslpins
requester.initialize("ssl", "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=")
```

## Check and request (GET/POST)

```
requester.Get("server.com/response.json")
```

## Event Response

```
Sub ssl_ready(R As String)
Log(R)
End Sub
```

