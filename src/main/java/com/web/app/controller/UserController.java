package com.web.app.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.app.model.User;
import com.web.app.service.UserService;
import com.web.app.session.UserSession;
import com.web.app.util.PaymentGateway;
import com.web.app.util.Validate;
import sun.misc.BASE64Encoder;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private Validate validate;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String LoginGET(Model model, HttpServletRequest req, HttpServletResponse res) {
		UserSession session = (UserSession) req.getSession().getAttribute("USER_SESSION");

		if (session != null) {
			model.addAttribute("message", "Login Successful");

			return "redirect:/";
		} else {
			model.addAttribute("message", "Please login");

			return "login";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String LoginPOST(Model model, HttpServletRequest req, HttpServletResponse res) throws Exception {
		String email = req.getParameter("email") == null ? "" : req.getParameter("email");
		String password = req.getParameter("password") == null ? "" : req.getParameter("password");

		boolean isEmailValid = validate.validateEmail(email);
		boolean isEmailExist = userService.emailExist(email);
		boolean isVerified = userService.isUserVerified(email);
		password = encrypt(password, "MD5", "UTF-8");

		if (isEmailValid) {
			if (isEmailExist) {
				if (isVerified) {
					User userDetails = userService.getUserDetails(email, password);
					if (userDetails != null) {
						UserSession session = new UserSession();
						session.setUser(userDetails);
						session.setSessionId(req.getSession().getId());
						req.getSession(true).setAttribute("USER_SESSION", session);

						model.addAttribute("message", "You have successfully login.");
					} else {
						model.addAttribute("message", "User details not found.");
					}
				} else {
					model.addAttribute("message", "User not verified");
				}
			} else {
				model.addAttribute("message", "Email doesn't exist. Please Register");
			}
		} else {
			model.addAttribute("message", "Email not valid");
		}

		return "redirect:/";
	}

	/* LOGOUT
	 * */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String Logout(Model model, HttpServletRequest req, HttpServletResponse res) {
		req.getSession().invalidate();

		return "redirect:/";
	}

	/* SINGUP GET
	 * */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String RegisterGet(Model model, HttpServletRequest req, HttpServletResponse res) {
		UserSession session = (UserSession) req.getSession().getAttribute("USER_SESSION");

		if (session != null) {
			return "redirect:/";
		} else {
			return "register";
		}
	}

	/* SINGUP POST
	 * */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String RegisterPOST(Model model, HttpServletRequest req, HttpServletResponse res) throws Exception {
		String email = req.getParameter("email") == null ? "" : req.getParameter("email");
		String password = req.getParameter("password") == null ? "" : req.getParameter("password");
		Integer verified = 1;
		String status = "enable";

		SecureRandom random = new SecureRandom();
		final String verifyCode = new BigInteger(130, random).toString(32);

		boolean isPasswordValid = validate.validatePassword(password);
		boolean isEmailValid = validate.validateEmail(email);
		boolean isEmailExist = userService.emailExist(email);

		if (isEmailValid) {
			if (!isEmailExist) {
				if (!password.equals("") && isPasswordValid) {
					password = encrypt(password, "MD5", "UTF-8");
					int userId = userService.registerUser(email, password, verifyCode, verified, status);

					model.addAttribute("message", "Account created successfully.");
				} else {
					model.addAttribute("message", "Password is not valid.");
				}
			} else {
				model.addAttribute("message", "Email already exist.");
			}
		} else {
			model.addAttribute("message", "Email is not valid.");
		}

		return "redirect:/";
	}

	/* ENCRYPY PASSWORD WITH MD5
	 * */
	public String encrypt(String plaintext, String algorithm, String encoding) throws Exception {
		MessageDigest msgDigest = null;
		String hashValue = null;
		try {
			msgDigest = MessageDigest.getInstance(algorithm);
			msgDigest.update(plaintext.getBytes(encoding));
			byte rawByte[] = msgDigest.digest();
			hashValue = (new BASE64Encoder()).encode(rawByte);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No Such Algorithm Exists");
		} catch (UnsupportedEncodingException e) {
			System.out.println("The Encoding Is Not Supported");
		}

		return hashValue;
	}

	@RequestMapping(value = "start-payment", method = RequestMethod.POST)
	public String startPayment(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		return "redirect:" + uri + "/app-1.0.0-BUILD-SNAPSHOT/initiate-payment";
	}

	@RequestMapping(value = "/initiate-payment", method = RequestMethod.GET)
	public void initiatePayment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TreeMap<String, String> parameters = new TreeMap<String, String>();
		PaymentGateway paymentGateway = new PaymentGateway();

		parameters.put("MID", PaymentGateway.MID); // Merchant ID (MID) provided by Paytm
		parameters.put("ORDER_ID", "MY_1234"); // Merchant’s order id
		parameters.put("CUST_ID", "CUST001"); // Customer ID registered with merchant
		parameters.put("TXN_AMOUNT", "100");
		parameters.put("MOBILE_NO", "9743232150");
		parameters.put("EMAIL", "nagarajtantri@gmail.com");
		parameters.put("CHANNEL_ID", PaymentGateway.CHANNEL_ID);
		parameters.put("INDUSTRY_TYPE_ID", PaymentGateway.INDUSTRY_TYPE_ID); //Provided by Paytm
		parameters.put("WEBSITE", PaymentGateway.WEBSITE); //Provided by Paytm
		parameters.put("CALLBACK_URL", "http://localhost:7999/app-1.0.0-BUILD-SNAPSHOT/validate");
		String checksum = paymentGateway.generateChecksum(parameters);

		StringBuilder strBuilder = buildPaymentInformation(parameters, checksum);
		response.setStatus(200);
		response.getOutputStream().write(strBuilder.toString().getBytes());
	}

	private StringBuilder buildPaymentInformation(TreeMap<String, String> parameters, String checksum) {
		StringBuilder outputHtml = new StringBuilder();
		outputHtml
				.append("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
		outputHtml.append("<html>");
		outputHtml.append("<head>");
		outputHtml.append("<title>Merchant Check Out Page</title>");
		outputHtml.append("</head>");
		outputHtml.append("<body>");
		outputHtml.append("<center><h1>Please do not refresh this page...</h1></center>");
		outputHtml.append("<form method='post' action='" + PaymentGateway.PAYTM_URL + "' name='f1'>");
		outputHtml.append("<table border='1'>");
		outputHtml.append("<tbody>");

		for (Map.Entry<String, String> item : parameters.entrySet()) {
			String key = item.getKey();
			String value = item.getValue();
			outputHtml.append("<input type='hidden' name='");
			outputHtml.append(key);
			outputHtml.append("' value='");
			outputHtml.append(value);
			outputHtml.append("'/><br/>");
		}
		outputHtml.append("<input type='hidden' name='CHECKSUMHASH' value='");
		outputHtml.append(checksum);
		outputHtml.append("'>");

		outputHtml.append("</tbody>");
		outputHtml.append("</table>");
		outputHtml.append("<script type='text/javascript'>");
		outputHtml.append("document.f1.submit();");
		outputHtml.append("</script>");
		outputHtml.append("</form>");
		outputHtml.append("</body>");
		outputHtml.append("</html>");

		return outputHtml;
	}
}
