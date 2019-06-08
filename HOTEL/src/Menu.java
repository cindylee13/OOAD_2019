import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/*
 * to do:
 * 1. Search的output 5~2 Star & Price(highest/lowest first)
 * 2. CheckInDate/CheckOutDate 日期限制
 * 3. 訂房成功＆失敗的output
 * 4. 輸入訂單編號不存在的錯誤訊息
 * 5. Inquiry的Modify跟Cancel
 * 
 * 
 * 1. 完成star5~2 price high price low （但顯示出來的總價似乎怪怪的 要不要檢查一下算總價的function
 * 2. 一次用出兩個日期框 會有些bug 還沒找到解決方法。 還是先分成兩個不一樣的框吧
 * 3. 完成
 * 4. 完成
 * 5. 完成：修改住宿日期，修改房間數，顯示修改成功訊息(同時顯示出修改後的訂單)，顯示失敗訊息，顯示取消訂單訊息
 */

public class Menu extends JPanel {
	private JLayeredPane layeredPane;
	private JLabel background = new JLabel();
	final int frameWidth = 1152, frameHeight = 720;

	// attribute of title
	private JPanel title = new JPanel();
	final private int titleWidth = 930, titleHeight = 80;
	final private Dimension titleCenter = new Dimension(frameWidth / 2, frameHeight / 4);
	private JLabel titleText = new JLabel("== HOTEL ==", JLabel.CENTER);

	// attribute of sub menu
	private JPanel subMenu = new JPanel();
	final private int subMenuWidth = 500, subMenuHeight = 70;
	final private Dimension subMenuCenter = new Dimension(frameWidth / 2, 524);
	private JLabel signinText = new JLabel("SIGN IN", JLabel.CENTER);
	private JLabel signupText = new JLabel("SIGN UP", JLabel.CENTER);

	// attribute of sign in error - UNKNOWN ID
	private JPanel Signinerror = new JPanel();
	final private int signinerrorWidth = 700, signinerrorHeight = 110;
	final private Dimension signinerrorCenter = new Dimension(frameWidth / 2, 500);
	private JLabel signinerrorText = new JLabel("UNKNOWN ID.", JLabel.CENTER);
	private JLabel backsigninerror = new JLabel("BACK", JLabel.CENTER);

	// attribute of sign in error - WRONG PASSWORD
	private JPanel Signinerror1 = new JPanel();
	final private int signinerror1Width = 700, signinerror1Height = 110;
	final private Dimension signinerror1Center = new Dimension(frameWidth / 2, 500);
	private JLabel signinerror1Text = new JLabel("WRONG PASSWORD.", JLabel.CENTER);
	private JLabel backsigninerror1 = new JLabel("BACK", JLabel.CENTER);

	// attribute of sign up error - USERID ALREADY EXISTS.
	private JPanel Signuperror = new JPanel();
	final private int signuperrorWidth = 500, signuperrorHeight = 110;
	final private Dimension signuperrorCenter = new Dimension(frameWidth / 2, 500);
	private JLabel signuperrorText = new JLabel("USERID ALREADY EXISTS.", JLabel.CENTER);
	private JLabel backsignuperror = new JLabel("BACK", JLabel.CENTER);

	// attribute of sign up error - WRONG VERIFY CODE.
	private JPanel Signuperror1 = new JPanel();
	final private int signuperror1Width = 500, signuperror1Height = 110;
	final private Dimension signuperrorCenter1 = new Dimension(frameWidth / 2, 500);
	private JLabel signuperror1Text = new JLabel("WRONG VERIFY CODE.", JLabel.CENTER);
	private JLabel backsignuperror1 = new JLabel("BACK", JLabel.CENTER);

	// attribute of sign in
	private JPanel Signin = new JPanel();
	final private int signinSetWidth = 500, signinSetHeight = 180;
	final private Dimension signinSetCenter = new Dimension(frameWidth / 2, 500);
	private JLabel signinlogin = new JLabel("LOGIN", JLabel.CENTER);
	private JLabel signinback = new JLabel("BACK", JLabel.CENTER);
	protected TextField signinidField = new TextField(15);
	protected TextField signinpasswordField = new TextField(15);

	// attribute of sign up
	private JPanel Signup = new JPanel();
	final private int signupSetWidth = 500, signupSetHeight = 240;
	final private Dimension signupSetCenter = new Dimension(frameWidth / 2, 500);
	private JLabel signuplogin = new JLabel("SIGN UP and LOGIN", JLabel.CENTER);
	private JLabel signupcancel = new JLabel("CANCEL", JLabel.CENTER);
	protected TextField signupidField = new TextField(13);
	protected TextField signuppasswordField = new TextField(13);
	protected TextField usercodeField = new TextField(8);
	protected JLabel verifycodeField = new JLabel("");

	// attribute of Hotel function Search/Reserve/Inquiry
	private JPanel Hotelfunction = new JPanel();
	final private int hotelfunctionWidth = 500, hotelfunctionHeight = 200;
	final private Dimension hotelfunctionCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel searchText = new JLabel("SEARCH", JLabel.CENTER);
	private JLabel reserveText = new JLabel("RESERVE", JLabel.CENTER);
	private JLabel inquiryText = new JLabel("INQUIRY", JLabel.CENTER);
	private JLabel logout = new JLabel("LOGOUT", JLabel.CENTER);

	// attribute of entering Search Date, People, Rooms
	private JPanel EnterSearch = new JPanel();
	final private int entersearchWidth = 700, entersearchlistHeight = 300;
	final private Dimension entersearchCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel backentersearch = new JLabel("BACK", JLabel.CENTER);
	private JLabel nextentersearch = new JLabel("NEXT", JLabel.CENTER);
	protected JTextField entercheckindateField = new JTextField(10);
	protected JTextField entercheckoutdateField = new JTextField(10);
	protected TextField enterpeopleField = new TextField(15);
	protected TextField enterroomField = new TextField(15);

	// attribute of invalid date error
	private JPanel Invalid_date_error = new JPanel();
	final private int invaiddateerrorWidth = 300, invaliddateerrorHeight = 75;
	final private Dimension invaliddateerrorCenter = new Dimension(frameWidth / 2, frameHeight / 5);
	private JLabel invaliddateerrorText = new JLabel("INVALID DATE!", JLabel.CENTER);

	// attribute of no matched hotel error
	private JPanel No_matched_hotel_error = new JPanel();
	final private int nomatchedhotelerrorWidth = 500, nomatchedhotelerrorHeight = 150;
	final private Dimension nomatchedhotelerrorCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel nomatchedhotelerrorText = new JLabel("NO MATCHED HOTEL!", JLabel.CENTER);
	private JLabel backnomatchedhotelerror = new JLabel("BACK", JLabel.CENTER);

	// attribute of Search
	private JPanel Search = new JPanel();
	final private int searchWidth = 570, searchHeight = 250;
	final private Dimension searchCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	JPanel star = new JPanel();
	private JLabel star5 = new JLabel("5-star", JLabel.CENTER);
	private JLabel star4 = new JLabel("4-star", JLabel.CENTER);
	private JLabel star3 = new JLabel("3-star", JLabel.CENTER);
	private JLabel star2 = new JLabel("2-star", JLabel.CENTER);
	private JLabel pricehighText = new JLabel("PRICE (HIGHEST FIRST)", JLabel.CENTER);
	private JLabel pricelowText = new JLabel("PRICE (LOWEST FIRST)", JLabel.CENTER);
	private JLabel backsearch = new JLabel("BACK", JLabel.CENTER);

	// attribute of Reserve
	private JPanel Reserve = new JPanel();
	final private int reserveWidth = 620, reserveHeight = 300;
	final private Dimension reserveCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JPanel reservebuttons = new JPanel();
	private JLabel cancelreserve = new JLabel("CANCEL", JLabel.CENTER);
	private JLabel backreserve = new JLabel("BACK", JLabel.CENTER);
	private JLabel nextreserve = new JLabel("NEXT", JLabel.CENTER);
	protected JTextField reservecheckindateField = new JTextField(10);
	protected JTextField reservecheckoutdateField = new JTextField(10);
	protected TextField reservesingleroomField = new TextField(2);
	protected TextField reservedoubleroomField = new TextField(2);
	protected TextField reservequadroomField = new TextField(2);
	protected JComboBox<Object> reservehotelid = new JComboBox<Object>();

	// attribute of reserve error (sold out)
	private JPanel Soldout = new JPanel();
	final private int soldoutWidth = 700, soldoutHeight = 150;
	final private Dimension soldoutCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel soldoutText = new JLabel("Sorry, NO VACANT SUITES!", JLabel.CENTER);
	private JLabel backsoldout = new JLabel("BACK", JLabel.CENTER);

	// attribute of reserve success
	private JPanel Reserve_success = new JPanel();
	final private int reservesuccessWidth = 600, reservesuccessHeight = 75;
	final private Dimension reservesuccessCenter = new Dimension(frameWidth / 2, frameHeight / 5);
	protected TextField successreservenumberField = new TextField(20);

	// attribute of inquiry
	private JPanel Inquiry = new JPanel();
	final private int InquiryWidth = 600, InquiryHeight = 150;
	final private Dimension InquiryCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel backinquiry = new JLabel("BACK", JLabel.CENTER);
	private JLabel nextinquiry = new JLabel("NEXT", JLabel.CENTER);
	protected TextField reservenumberField = new TextField(15);

	// attribute of wrong reservation number
	private JPanel Wrong_reservation_number = new JPanel();
	final private int wrongreservationnumberWidth = 700, wrongreservationnumberHeight = 150;
	final private Dimension wrongreservationnumberCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel wrongreservationnumberText = new JLabel("WRONG RESERVATION NUMBER!", JLabel.CENTER);
	private JLabel backwrongreservationnumber = new JLabel("BACK", JLabel.CENTER);

	// attribute of reserve order (reservation record and modify and cancel
	// reservation)
	private JPanel Reserveorder = new JPanel();
	final private int reserveorderWidth = 650, reserveorderHeight = 300;
	final private Dimension reserveorderCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel modifyText = new JLabel("MODIFY", JLabel.CENTER);
	private JLabel cancelText = new JLabel("CANCEL", JLabel.CENTER);
	private JLabel confirmText = new JLabel("CONFIRM", JLabel.CENTER);
	protected TextField reserveorderhotelIDField = new TextField(15);
	protected TextField reserveordersingleroomField = new TextField(2);
	protected TextField reserveorderdoubleroomField = new TextField(2);
	protected TextField reserveorderquadroomField = new TextField(2);
	protected JTextField reserveordercheckindateField = new JTextField(10);
	protected JTextField reserveordercheckoutdateField = new JTextField(10);
	protected TextField reserveorderstaynightField = new TextField(2);
	protected TextField reserveorderpriceField = new TextField(5);
	JPanel reserveorderbuttons = new JPanel();

	// attribute of hotel list
	private JPanel Hotellist = new JPanel();
	final private int hotellistWidth = 750, hotellistHeight = 500;
	final private Dimension hotellistCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	JTable HotellistTable = new JTable();
	String[] heading = new String[] { "ID", "Star", "City", "Address", "Single", "Double", "Quad", "Price" };
	private JLabel backhotellist = new JLabel("BACK", JLabel.CENTER);
	private JLabel reservehotellist = new JLabel("RESERVE", JLabel.CENTER);

	// attribute of modify
	private JPanel Modify = new JPanel();
	final private int modifyWidth = 300, modifyHeight = 200;
	final private Dimension modifyCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel changeroomText = new JLabel("CHANGE ROOM", JLabel.CENTER);
	private JLabel revisedateText = new JLabel("REVISE DATE", JLabel.CENTER);

	// attribute of change room
	private JPanel Changeroom = new JPanel();
	final private int changeroomWidth = 600, changeroomHeight = 240;
	final private Dimension changeroomCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	protected TextField originsingleroomField = new TextField(2); // 原房間數
	protected TextField origindoubleroomField = new TextField(2);
	protected TextField originquadroomField = new TextField(2);
	protected TextField newsingleroomField = new TextField(2); // 新房間數
	protected TextField newdoubleroomField = new TextField(2);
	protected TextField newquadroomField = new TextField(2);
	private JLabel cancelchangeroom = new JLabel("CANCEL", JLabel.CENTER);
	private JLabel backchangeroom = new JLabel("BACK", JLabel.CENTER);
	private JLabel nextchangeroom = new JLabel("NEXT", JLabel.CENTER);

	// attribute of revise date
	private JPanel Revisedate = new JPanel();
	final private int revisedateWidth = 820, revisedateHeight = 180;
	final private Dimension revisedateCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	protected JTextField origincheckindateField = new JTextField(7); // 原日期
	protected JTextField origincheckoutdateField = new JTextField(7);
	protected JTextField newcheckindateField = new JTextField(10); // 新日期
	protected JTextField newcheckoutdateField = new JTextField(10);
	private JLabel cancelrevisedate = new JLabel("CANCEL", JLabel.CENTER);
	private JLabel backrevisedate = new JLabel("BACK", JLabel.CENTER);
	private JLabel nextrevisedate = new JLabel("NEXT", JLabel.CENTER);

	// attribute of reduce room / revise date success
	private JPanel ChangeRevise_success = new JPanel();
	final private int changerevisesuccessWidth = 800, changerevisesuccessHeight = 75;
	final private Dimension changerevisesuccessCenter = new Dimension(frameWidth / 2, frameHeight / 5);
	private JLabel changerevisesuccessText = new JLabel("THE FOLOWING IS YOUR REVISED BOOKING ORDER.", JLabel.CENTER);

	// attribute of cancel order success
	private JPanel Cancelorder_success = new JPanel();
	final private int cancelordersuccessWidth = 800, cancelordersuccessHeight = 150;
	final private Dimension cancelordersuccessCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel cancelordersuccessText = new JLabel("YOUR ORDER HAS BEEN CANCELED!", JLabel.CENTER);
	private JLabel confirmcancelordersuccess = new JLabel("CONFIRM", JLabel.CENTER);

	// change room error
	private JPanel Changeroom_error = new JPanel();
	final private int changeroomerrorWidth = 800, changeroomerrorHeight = 75;
	final private Dimension changeroomerrorCenter = new Dimension(frameWidth / 2, frameHeight / 4);
	private JLabel changeroomerrorText = new JLabel("SORRY, BOOKING MORE ROOMS IS UNAVAILABLE!", JLabel.CENTER);

	// reduce room error
	private JPanel Revisedate_error = new JPanel();
	final private int revisedateerrorWidth = 800, revisedateerrorHeight = 75;
	final private Dimension revisedateerrorCenter = new Dimension(frameWidth / 2, frameHeight / 4);
	private JLabel revisedateerrorText = new JLabel("SORRY, EXTEND LODGING DAYS IS UNAVAILABLE!", JLabel.CENTER);

	// Menu(Panel) settings
	private void initPanel() {
		setLayout(new GridLayout(1, 1));
		setOpaque(false);
	}

	// title
	private void initTitle() {
		titleText.setFont(new Font("Arial", Font.BOLD, 60));
		signinText.setFont(new Font("Arial Black", Font.BOLD, 30));
		signupText.setFont(new Font("Arial Black", Font.BOLD, 30));

		title.setLayout(new GridLayout(1, 1, 0, 0));
		title.setOpaque(false);
		titleText.setForeground(new Color(65, 105, 225));
		titleText.setOpaque(false);
//		titleText.setBackground(new Color(176, 196, 222));
		titleText.setBorder(new EmptyBorder(5, 5, 5, 5));
		title.add(titleText);
	}

	// sign in error
	private void initSigninerror() {
		signinerrorText.setFont(new Font("Arial", Font.BOLD, 28));
		signinerrorText.setForeground(new Color(255, 0, 0));
		backsigninerror.setFont(new Font("Arial Black", Font.BOLD, 28));
		Signinerror.setLayout(new GridLayout(2, 1, 0, 0));
		Signinerror.setOpaque(false);
		Signinerror.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Signinerror.add(signinerrorText);
		Signinerror.add(backsigninerror);
	}

	// sign in error1
	private void initSigninerror1() {
		signinerror1Text.setFont(new Font("Arial", Font.BOLD, 28));
		signinerror1Text.setForeground(new Color(255, 0, 0));
		backsigninerror1.setFont(new Font("Arial Black", Font.BOLD, 28));
		Signinerror1.setLayout(new GridLayout(2, 1, 0, 0));
		Signinerror1.setOpaque(false);
		Signinerror1.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Signinerror1.add(signinerror1Text);
		Signinerror1.add(backsigninerror1);
	}

	// sign up error
	private void initSignuperror() {
		signuperrorText.setFont(new Font("Arial", Font.BOLD, 28));
		signuperrorText.setForeground(new Color(255, 0, 0));
		backsignuperror.setFont(new Font("Arial Black", Font.BOLD, 28));
		Signuperror.setLayout(new GridLayout(2, 1, 0, 0));
		Signuperror.setOpaque(false);
		Signuperror.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Signuperror.add(signuperrorText);
		Signuperror.add(backsignuperror);
	}

	private void initSignuperror1() {
		signuperror1Text.setFont(new Font("Arial", Font.BOLD, 28));
		signuperror1Text.setForeground(new Color(255, 0, 0));
		backsignuperror1.setFont(new Font("Arial Black", Font.BOLD, 28));
		Signuperror1.setLayout(new GridLayout(2, 1, 0, 0));
		Signuperror1.setOpaque(false);
		Signuperror1.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Signuperror1.add(signuperror1Text);
		Signuperror1.add(backsignuperror);
	}

	// sign in
	private void initSignIn() {
		Signin.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Signin.setLayout(new GridLayout(3, 1));
		Signin.setOpaque(false);

		// enter ID Panel setting
		JPanel IDPanel = new JPanel();
		IDPanel.setOpaque(false);
		IDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		// enter ID
		JLabel ID = new JLabel("       ID       : ");
		ID.setFont(new Font("Arial Black", Font.PLAIN, 20));
		signinidField.setEditable(true);
		signinidField.setFont(new Font("Arial Black", Font.BOLD, 23));
		// ID Panel adding
		IDPanel.add(ID);
		IDPanel.add(signinidField);

		// enter password Panel setting
		JPanel passwordPanel = new JPanel();
		passwordPanel.setOpaque(false);
		passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		// enter password
		JLabel password = new JLabel("PASSWORD : ");
		password.setFont(new Font("Arial Black", Font.PLAIN, 20));
		signinpasswordField.setEchoChar('●');
		signinpasswordField.setEditable(true);
		signinpasswordField.setFont(new Font("Arial Black", Font.BOLD, 23));
		// password Panel adding
		passwordPanel.add(password);
		passwordPanel.add(signinpasswordField);

		// set 'back' and 'login' button
		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		signinlogin.setFont(new Font("Arial Black", Font.PLAIN, 15));
		signinback.setFont(new Font("Arial Black", Font.PLAIN, 15));
		buttons.setLayout(new GridLayout(1, 2));
		buttons.add(signinback);
		buttons.add(signinlogin);

		// sign in adding
		Signin.add(IDPanel);
		Signin.add(passwordPanel);
		Signin.add(buttons);
	}

	// sign up
	private void initSignUp() {
		Signup.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Signup.setLayout(new GridLayout(4, 1));
		Signup.setOpaque(false);

		// set ID
		JPanel IDPanel = new JPanel();
		IDPanel.setOpaque(false);
		IDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel ID = new JLabel("         ID         : ");
		ID.setFont(new Font("Arial Black", Font.PLAIN, 20));
		signupidField.setEditable(true);
		signupidField.setFont(new Font("Arial Black", Font.BOLD, 23));
		IDPanel.add(ID);
		IDPanel.add(signupidField);

		// set password
		JPanel passwordPanel = new JPanel();
		passwordPanel.setOpaque(false);
		passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel password = new JLabel("PASSWORD   : ");
		password.setFont(new Font("Arial Black", Font.PLAIN, 20));
		signuppasswordField.setEchoChar('●');
		signuppasswordField.setEditable(true);
		signuppasswordField.setFont(new Font("Arial Black", Font.BOLD, 23));
		passwordPanel.add(password);
		passwordPanel.add(signuppasswordField);

		// verify code Panel
		JPanel verifycodePanel = new JPanel();
		verifycodePanel.setOpaque(false);
		verifycodePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		// enter verify code
		JLabel verifycode = new JLabel("VERIFY CODE : ");
		verifycode.setFont(new Font("Arial Black", Font.PLAIN, 20));
		usercodeField.setEditable(true);
		usercodeField.setFont(new Font("Times New Roman", Font.BOLD, 23));
		usercodeField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				String s = usercodeField.getText();
				if (s.length() >= 6)
					e.consume();
			}
		});
		verifycodeField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		// verify code panel adding
		verifycodePanel.add(verifycode);
		verifycodePanel.add(usercodeField);
		verifycodePanel.add(verifycodeField);

		// set 'cancel' and 'sign up and login' button
		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		buttons.setLayout(new GridLayout(1, 2));
		signuplogin.setFont(new Font("Arial Black", Font.PLAIN, 15));
		signupcancel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		buttons.add(signupcancel);
		buttons.add(signuplogin);

		// sign up adding
		Signup.add(IDPanel);
		Signup.add(passwordPanel);
		Signup.add(verifycodePanel);
		Signup.add(buttons);

	}

	// hotel function hotel list/reserve/inquiry
	private void initHotelfunction() {
		searchText.setFont(new Font("Arial Black", Font.BOLD, 30));
		reserveText.setFont(new Font("Arial Black", Font.BOLD, 30));
		inquiryText.setFont(new Font("Arial Black", Font.BOLD, 30));
		logout.setFont(new Font("Arial Black", Font.BOLD, 30));
		Hotelfunction.setLayout(new GridLayout(2, 2, 0, 0));
		Hotelfunction.setOpaque(false);
		Hotelfunction.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Hotelfunction.add(searchText);
		Hotelfunction.add(reserveText);
		Hotelfunction.add(inquiryText);
		Hotelfunction.add(logout);
	}

	// enter hotel list date/people/rooms
	private void initEnterSearch() {
		EnterSearch.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		EnterSearch.setLayout(new GridLayout(5, 1));
		EnterSearch.setOpaque(false);
		// check in date panel
		JPanel checkinPanel = new JPanel();
		checkinPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		checkinPanel.setOpaque(false);
		checkinPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		// enter check in date
		JLabel checkin = new JLabel("  CHECK IN DATE: ");
		checkin.setFont(new Font("Arial Black", Font.PLAIN, 20));
		// setting check in yyyy/mm/dd
		entercheckindateField.setEditable(false);
		entercheckindateField.setFont(new Font("Serif", Font.BOLD, 23));
		entercheckindateField.setBackground(new Color(255, 255, 255));
		entercheckindateField.setText("SELECT A DATE");
		entercheckindateField.setOpaque(true);
		entercheckindateField.setBounds(267, 15, 105, 40);
		entercheckindateField.setColumns(10);
		entercheckindateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup DP = new DatePopup(entercheckindateField);
				DP.showDialog();
			}
		});
		// check in panel adding
		checkinPanel.add(checkin);
		checkinPanel.add(entercheckindateField);

		// check out date panel
		JPanel checkoutPanel = new JPanel();
		checkoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		checkoutPanel.setOpaque(false);
		checkoutPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		JLabel checkout = new JLabel("  CHECK OUT DATE: ");
		checkout.setFont(new Font("Arial Black", Font.PLAIN, 20));
		// setting check in yyyy/mm/dd
		entercheckoutdateField.setEditable(false);
		entercheckoutdateField.setFont(new Font("Serif", Font.BOLD, 23));
		entercheckoutdateField.setBackground(new Color(255, 255, 255));
		entercheckoutdateField.setText("SELECT A DATE");
		entercheckoutdateField.setOpaque(true);
		entercheckoutdateField.setBounds(267, 15, 105, 40);
		entercheckoutdateField.setColumns(10);
		entercheckoutdateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup DP = new DatePopup(entercheckoutdateField);
				DP.showDialog();
			}
		});
		// check out panel adding
		checkoutPanel.add(checkout);
		checkoutPanel.add(entercheckoutdateField);

		// people panel
		JPanel peoplePanel = new JPanel();
		peoplePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		peoplePanel.setOpaque(false);
		peoplePanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		JLabel people = new JLabel("NUMBER OF PEOPLE: ");
		people.setFont(new Font("Arial Black", Font.PLAIN, 20));
		enterpeopleField.setEditable(true);
		enterpeopleField.setFont(new Font("Serif", Font.BOLD, 23));
		enterpeopleField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		// people panel adding
		peoplePanel.add(people);
		peoplePanel.add(enterpeopleField);

		// room panel
		JPanel roomPanel = new JPanel();
		roomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		roomPanel.setOpaque(false);
		roomPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		JLabel room = new JLabel("NUMBER OF ROOMS: ");
		room.setFont(new Font("Arial Black", Font.PLAIN, 20));
		enterroomField.setEditable(true);
		enterroomField.setFont(new Font("Serif", Font.BOLD, 23));
		enterroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		// room panel adding
		roomPanel.add(room);
		roomPanel.add(enterroomField);
		// set 'back' and 'next' button
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		backentersearch.setFont(new Font("Arial Black", Font.PLAIN, 20));
		nextentersearch.setFont(new Font("Arial Black", Font.PLAIN, 20));
		buttons.add(backentersearch);
		buttons.add(nextentersearch);
		// EnterHotellist adding
		EnterSearch.add(checkinPanel);
		EnterSearch.add(checkoutPanel);
		EnterSearch.add(peoplePanel);
		EnterSearch.add(roomPanel);
		EnterSearch.add(buttons);
	}

	// enter hotel list error (select wrong date)
	private void initEnterinvaliddateerror() {
		Invalid_date_error.setLayout(new GridLayout(1, 1, 0, 0));
		Invalid_date_error.setOpaque(false);
//		Enter_invalid_date_error.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		invaliddateerrorText.setFont(new Font("Dialog", Font.BOLD, 30));
		invaliddateerrorText.setForeground(new Color(255, 0, 0));
		Invalid_date_error.add(invaliddateerrorText);
	}

	// search hotel
	private void initSearch() {
		// set font
		star5.setFont(new Font("Arial Black", Font.BOLD, 28));
		star4.setFont(new Font("Arial Black", Font.BOLD, 28));
		star3.setFont(new Font("Arial Black", Font.BOLD, 28));
		star2.setFont(new Font("Arial Black", Font.BOLD, 28));
		pricehighText.setFont(new Font("Arial Black", Font.BOLD, 28));
		pricelowText.setFont(new Font("Arial Black", Font.BOLD, 28));
		backsearch.setFont(new Font("Arial Black", Font.BOLD, 28));
		Search.setLayout(new GridLayout(4, 1, 0, 0));
		Search.setOpaque(false);
		Search.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		star.setLayout(new GridLayout(1, 4, 0, 0));
		star.setOpaque(false);
		star.add(star5);
		star.add(star4);
		star.add(star3);
		star.add(star2);
		Search.add(star);
		Search.add(pricehighText);
		Search.add(pricelowText);
		Search.add(backsearch);
	}

	// hotel list
	private void initHotellist() {
		Hotellist.setLayout(new BorderLayout());
//		Hotellist.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Hotellist.setOpaque(false);
		backhotellist.setFont(new Font("Arial Black", Font.BOLD, 30));
		backhotellist.setBackground(new Color(245, 255, 250));
		reservehotellist.setFont(new Font("Arial Black", Font.BOLD, 30));
		reservehotellist.setBackground(new Color(245, 255, 250));
	}

	// search hotel error (No matched Hotel)
	private void initNomatchedhotelerror() {
		nomatchedhotelerrorText.setFont(new Font("Dialog", Font.BOLD, 28));
		nomatchedhotelerrorText.setForeground(new Color(255, 0, 0));
		nomatchedhotelerrorText.setBorder(new EmptyBorder(20, 40, 20, 40));
		backnomatchedhotelerror.setFont(new Font("Arial Black", Font.BOLD, 28));
		backnomatchedhotelerror.setBorder(new EmptyBorder(20, 40, 20, 40));
		No_matched_hotel_error.setLayout(new GridLayout(2, 1, 0, 0));
		No_matched_hotel_error.setOpaque(false);
		No_matched_hotel_error.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		No_matched_hotel_error.add(nomatchedhotelerrorText);
		No_matched_hotel_error.add(backnomatchedhotelerror);
	}

	// Reserve
	private void initReserve() {
		Reserve.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Reserve.setLayout(new GridLayout(5, 1));
		Reserve.setOpaque(false);

		// check in date panel
		JPanel checkinPanel = new JPanel();
		checkinPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		checkinPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		checkinPanel.setOpaque(false);
		// enter check in date
		JLabel checkin = new JLabel("  CHECK IN DATE: ");
		checkin.setFont(new Font("Arial Black", Font.PLAIN, 20));
		// setting check in yyyy/mm/dd
		reservecheckindateField.setEditable(false);
		reservecheckindateField.setFont(new Font("Serif", Font.BOLD, 23));
		reservecheckindateField.setBackground(new Color(255, 255, 255));
		reservecheckindateField.setText("SELECT DATE");
		reservecheckindateField.setOpaque(true);
		reservecheckindateField.setBounds(267, 15, 105, 40);
		reservecheckindateField.setColumns(10);
		reservecheckindateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup DP = new DatePopup(reservecheckindateField);
				DP.showDialog();
			}
		});
		// check in panel adding
		checkinPanel.add(checkin);
		checkinPanel.add(reservecheckindateField);

		// check out date panel
		JPanel checkoutPanel = new JPanel();
		checkoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		checkoutPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		checkoutPanel.setOpaque(false);
		// enter check out date
		JLabel checkout = new JLabel("  CHECK OUT DATE: ");
		checkout.setFont(new Font("Arial Black", Font.PLAIN, 20));
		// setting check in yyyy/mm/dd
		reservecheckoutdateField.setEditable(false);
		reservecheckoutdateField.setFont(new Font("Serif", Font.BOLD, 23));
		reservecheckoutdateField.setBackground(new Color(255, 255, 255));
		reservecheckoutdateField.setText("SELECT DATE");
		reservecheckoutdateField.setOpaque(true);
		reservecheckoutdateField.setBounds(267, 15, 105, 40);
		reservecheckoutdateField.setColumns(10);
		reservecheckoutdateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup DP = new DatePopup(reservecheckoutdateField);
				DP.showDialog();
			}
		});
		// check out panel adding
		checkoutPanel.add(checkout);
		checkoutPanel.add(reservecheckoutdateField);

		// hotelID Panel
		JPanel hotelIDPanel = new JPanel();
		hotelIDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		hotelIDPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		hotelIDPanel.setOpaque(false);
		// select hotel ID
		JLabel hotelID = new JLabel("    HotelID     : ");
		hotelID.setFont(new Font("Arial Black", Font.PLAIN, 20));
		String[] option = new String[1500];
		for (Integer i = 0; i < 1500; i++) {
			option[i] = i.toString();
		}
		reservehotelid = new JComboBox<Object>(option);
		hotelIDPanel.add(hotelID);
		hotelIDPanel.add(reservehotelid);

		// number of room panel
		JPanel roomPanel = new JPanel();
		roomPanel.setLayout(new GridLayout(1, 6));
		roomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		roomPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		roomPanel.setOpaque(false);
		// single room
		JLabel singleroom = new JLabel("Single: ");
		singleroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reservesingleroomField.setEditable(true);
		reservesingleroomField.setFont(new Font("Serif", Font.BOLD, 23));
		reservesingleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		// double room
		JLabel doubleroom = new JLabel("Double: ");
		doubleroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reservedoubleroomField.setEditable(true);
		reservedoubleroomField.setFont(new Font("Serif", Font.BOLD, 23));
		reservedoubleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		// quad room
		JLabel quadroom = new JLabel("Quad: ");
		quadroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reservequadroomField.setEditable(true);
		reservequadroomField.setFont(new Font("Serif", Font.BOLD, 23));
		reservequadroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		// room panel adding
		roomPanel.add(singleroom);
		roomPanel.add(reservesingleroomField);
		roomPanel.add(doubleroom);
		roomPanel.add(reservedoubleroomField);
		roomPanel.add(quadroom);
		roomPanel.add(reservequadroomField);

		// setting 'back' and 'next' buttons
		reservebuttons.setLayout(new GridLayout(1, 3));
		reservebuttons.setOpaque(false);
		cancelreserve.setFont(new Font("Arial Black", Font.PLAIN, 20));
		backreserve.setFont(new Font("Arial Black", Font.PLAIN, 20));
		nextreserve.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reservebuttons.add(cancelreserve);
//		reservebuttons.add(backreserve);
		reservebuttons.add(nextreserve);

		// Reserve adding Panel
		Reserve.add(checkinPanel);
		Reserve.add(checkoutPanel);
		Reserve.add(hotelIDPanel);
		Reserve.add(roomPanel);
		Reserve.add(reservebuttons);
	}

	// Reserve success
	private void initReservesuccess() {
		Reserve_success.setLayout(new GridLayout(1, 1, 0, 0));
		Reserve_success.setOpaque(false);
//		Reserve_success.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));

		JPanel reservenumberPanel = new JPanel();
		reservenumberPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		reservenumberPanel.setOpaque(false);
		reservenumberPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		JLabel reservenumber = new JLabel("SUCCEED! RESERVATION NUMBER : ");
		reservenumber.setFont(new Font("Dialog", Font.PLAIN, 18));
		successreservenumberField.setFont(new Font("Serif", Font.BOLD, 18));
		reservenumberPanel.add(reservenumber);
		reservenumberPanel.add(successreservenumberField);

		Reserve_success.add(reservenumberPanel);
	}

	// sold out
	private void initSoldout() {
		soldoutText.setFont(new Font("Dialog", Font.BOLD, 28));
		soldoutText.setForeground(new Color(255, 0, 0));
		soldoutText.setBorder(new EmptyBorder(20, 40, 20, 40));
		backsoldout.setFont(new Font("Arial Black", Font.BOLD, 28));
		backsoldout.setBorder(new EmptyBorder(20, 40, 20, 40));
		Soldout.setLayout(new GridLayout(2, 1, 0, 0));
		Soldout.setOpaque(false);
		Soldout.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Soldout.add(soldoutText);
		Soldout.add(backsoldout);
	}

	// inquiry
	private void initInquiry() {
		Inquiry.setLayout(new GridLayout(2, 1, 0, 0));
		Inquiry.setOpaque(false);
		Inquiry.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));

		// enter reserve number
		JPanel reservenumberPanel = new JPanel();
		reservenumberPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		reservenumberPanel.setOpaque(false);
		reservenumberPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		JLabel reservenumber = new JLabel("RESERVATION NUMBER : ");
		reservenumber.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reservenumberField.setFont(new Font("Serif", Font.BOLD, 23));
		reservenumberField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		// reservenumberPanel adding
		reservenumberPanel.add(reservenumber);
		reservenumberPanel.add(reservenumberField);

		// set 'back' and 'next' buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 3));
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		backinquiry.setFont(new Font("Arial Black", Font.PLAIN, 23));
		nextinquiry.setFont(new Font("Arial Black", Font.PLAIN, 23));
		buttons.add(backinquiry);
		buttons.add(nextinquiry);

		// inquiry adding
		Inquiry.add(reservenumberPanel);
		Inquiry.add(buttons);
	}

	// wrong reservation number
	private void initWrongreservationnumber() {
		wrongreservationnumberText.setFont(new Font("Arial", Font.BOLD, 28));
		wrongreservationnumberText.setForeground(new Color(255, 0, 0));
		wrongreservationnumberText.setBorder(new EmptyBorder(20, 40, 20, 40));
		backwrongreservationnumber.setFont(new Font("Arial Black", Font.BOLD, 28));
		backwrongreservationnumber.setBorder(new EmptyBorder(20, 40, 20, 40));
		Wrong_reservation_number.setLayout(new GridLayout(2, 1, 0, 0));
		Wrong_reservation_number.setOpaque(false);
		Wrong_reservation_number.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Wrong_reservation_number.add(wrongreservationnumberText);
		Wrong_reservation_number.add(backwrongreservationnumber);
	}

	// reserve order
	private void initReserveorder() {
		Reserveorder.setLayout(new GridLayout(5, 1, 0, 0));
		Reserveorder.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Reserveorder.setOpaque(false);

		// hotelID Panel
		JPanel hotelIDPanel = new JPanel();
		hotelIDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		hotelIDPanel.setOpaque(false);
		hotelIDPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		// enter hotel ID
		JLabel hotelID = new JLabel("    HotelID     : ");
		hotelID.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reserveorderhotelIDField.setFont(new Font("Serif", Font.BOLD, 23));
		reserveorderhotelIDField.setEditable(false);
		// hotel ID Panel adding
		hotelIDPanel.add(hotelID);
		hotelIDPanel.add(reserveorderhotelIDField);

		// number of room panel
		JPanel roomPanel = new JPanel();
		roomPanel.setLayout(new GridLayout(1, 6));
		roomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		roomPanel.setOpaque(false);
		roomPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		// single room
		JLabel singleroom = new JLabel("Single: ");
		singleroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reserveordersingleroomField.setEditable(false);
		reserveordersingleroomField.setFont(new Font("Serif", Font.BOLD, 23));
		reserveordersingleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		// double room
		JLabel doubleroom = new JLabel("Double: ");
		doubleroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reserveorderdoubleroomField.setEditable(false);
		reserveorderdoubleroomField.setFont(new Font("Serif", Font.BOLD, 23));
		reserveorderdoubleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		// quad room
		JLabel quadroom = new JLabel("Quad: ");
		quadroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reserveorderquadroomField.setEditable(false);
		reserveorderquadroomField.setFont(new Font("Serif", Font.BOLD, 23));
		reserveorderquadroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		// room panel adding
		roomPanel.add(singleroom);
		roomPanel.add(reserveordersingleroomField);
		roomPanel.add(doubleroom);
		roomPanel.add(reserveorderdoubleroomField);
		roomPanel.add(quadroom);
		roomPanel.add(reserveorderquadroomField);

		// lodging date panel
		JPanel lodgingPanel = new JPanel();
		lodgingPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		lodgingPanel.setOpaque(false);
		lodgingPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		reserveordercheckindateField.setFont(new Font("Serif", Font.BOLD, 23));
		reserveordercheckindateField.setEditable(false);
		JLabel mark = new JLabel("~");
		mark.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reserveordercheckoutdateField.setFont(new Font("Serif", Font.BOLD, 23));
		reserveordercheckoutdateField.setEditable(false);
		// lodgingPanel adding
		lodgingPanel.add(reserveordercheckindateField);
		lodgingPanel.add(mark);
		lodgingPanel.add(reserveordercheckoutdateField);

		// 'total length of stay' and 'total price'
		JPanel staypricePanel = new JPanel();
		staypricePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		staypricePanel.setOpaque(false);
		staypricePanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		JLabel stay = new JLabel("Total Nights of Stay:");
		stay.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reserveorderstaynightField.setFont(new Font("Serif", Font.BOLD, 23));
		reserveorderstaynightField.setEditable(false);
		JLabel price = new JLabel("Total Price:");
		price.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reserveorderpriceField.setFont(new Font("Serif", Font.BOLD, 23));
		reserveorderpriceField.setEditable(false);
		// stay price Panel adding
		staypricePanel.add(stay);
		staypricePanel.add(reserveorderstaynightField);
		staypricePanel.add(price);
		staypricePanel.add(reserveorderpriceField);

		// set 'back' and 'next' button
		reserveorderbuttons.setLayout(new GridLayout(1, 3));
		reserveorderbuttons.setOpaque(false);
		reserveorderbuttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		modifyText.setFont(new Font("Arial Black", Font.PLAIN, 20));
		cancelText.setFont(new Font("Arial Black", Font.PLAIN, 20));
		confirmText.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reserveorderbuttons.add(cancelText);
		reserveorderbuttons.add(modifyText);
		reserveorderbuttons.add(confirmText);

		// MCR adding
		Reserveorder.add(hotelIDPanel);
		Reserveorder.add(roomPanel);
		Reserveorder.add(lodgingPanel);
		Reserveorder.add(staypricePanel);
		Reserveorder.add(reserveorderbuttons);
	}

	// modify (reduce room or revise date)
	private void initModify() {
		changeroomText.setFont(new Font("Arial Black", Font.BOLD, 28));
		revisedateText.setFont(new Font("Arial Black", Font.BOLD, 28));
		Modify.setLayout(new GridLayout(2, 1, 0, 0));
		Modify.setOpaque(false);
		Modify.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Modify.add(changeroomText);
		Modify.add(revisedateText);
	}

	// change room
	private void initChangeroom() {
		Changeroom.setLayout(new GridLayout(4, 1, 0, 0));
		Changeroom.setOpaque(false);
		Changeroom.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		// single
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel1.setOpaque(false);
		panel1.setBorder(new EmptyBorder(10, 30, 10, 30));
		JLabel change1 = new JLabel("CHANGE SINGLE ROOM FROM");
		change1.setFont(new Font("Arial Black", Font.PLAIN, 20));
		JLabel to = new JLabel("to");
		to.setFont(new Font("Arial Black", Font.PLAIN, 20));
		originsingleroomField.setEditable(false);
		originsingleroomField.setFont(new Font("Arial Black", Font.BOLD, 23));
		newsingleroomField.setEditable(true);
		newsingleroomField.setFont(new Font("Arial Black", Font.BOLD, 23));
		newsingleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		panel1.add(change1);
		panel1.add(originsingleroomField);
		panel1.add(to);
		panel1.add(newsingleroomField);
		// double
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2.setOpaque(false);
		panel2.setBorder(new EmptyBorder(10, 30, 10, 30));
		JLabel change2 = new JLabel("CHANGE DOUBLE ROOM FROM");
		change2.setFont(new Font("Arial Black", Font.PLAIN, 20));
		JLabel to2 = new JLabel("to");
		to2.setFont(new Font("Arial Black", Font.PLAIN, 20));
		origindoubleroomField.setEditable(false);
		origindoubleroomField.setFont(new Font("Arial Black", Font.BOLD, 23));
		newdoubleroomField.setEditable(true);
		newdoubleroomField.setFont(new Font("Arial Black", Font.BOLD, 23));
		newdoubleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		panel2.add(change2);
		panel2.add(origindoubleroomField);
		panel2.add(to2);
		panel2.add(newdoubleroomField);
		// quad room
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3.setOpaque(false);
		panel3.setBorder(new EmptyBorder(10, 30, 10, 30));
		JLabel change3 = new JLabel("CHANGE QUAD ROOM FROM");
		change3.setFont(new Font("Arial Black", Font.PLAIN, 20));
		JLabel to3 = new JLabel("to");
		to3.setFont(new Font("Arial Black", Font.PLAIN, 20));
		originquadroomField.setEditable(false);
		originquadroomField.setFont(new Font("Arial Black", Font.BOLD, 23));
		newquadroomField.setEditable(true);
		newquadroomField.setFont(new Font("Arial Black", Font.BOLD, 23));
		newquadroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		panel3.add(change3);
		panel3.add(originquadroomField);
		panel3.add(to3);
		panel3.add(newquadroomField);
		// set 'back' and 'next' button
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 3));
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		cancelchangeroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		backchangeroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		nextchangeroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		buttons.add(cancelchangeroom);
		buttons.add(backchangeroom);
		buttons.add(nextchangeroom);
		// adding panel
		Changeroom.add(panel1);
		Changeroom.add(panel2);
		Changeroom.add(panel3);
		Changeroom.add(buttons);
	}

	// revise date
	private void initRevisedate() {
		Revisedate.setLayout(new GridLayout(3, 1, 0, 0));
		Revisedate.setOpaque(false);
		Revisedate.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		// original date
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel1.setOpaque(false);
		panel1.setBorder(new EmptyBorder(10, 30, 10, 30));
		JLabel change1 = new JLabel("CHANGE DATE FROM");
		change1.setFont(new Font("Arial Black", Font.PLAIN, 20));
		JLabel to = new JLabel("~");
		to.setFont(new Font("Arial Black", Font.PLAIN, 20));
		origincheckindateField.setEditable(false);
		origincheckindateField.setFont(new Font("Serif", Font.PLAIN, 23));
		origincheckoutdateField.setEditable(false);
		origincheckoutdateField.setFont(new Font("Serif", Font.PLAIN, 23));
		// panel1 adding
		panel1.add(change1);
		panel1.add(origincheckindateField);
		panel1.add(to);
		panel1.add(origincheckoutdateField);

		// new date
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2.setOpaque(false);
		panel2.setBorder(new EmptyBorder(10, 30, 10, 30));
		JLabel change2 = new JLabel("TO");
		change2.setFont(new Font("Arial Black", Font.PLAIN, 20));
		JLabel to2 = new JLabel("~");
		to2.setFont(new Font("Arial Black", Font.PLAIN, 20));
		// setting check in yyyy/mm/dd
		newcheckindateField.setEditable(true);
		newcheckindateField.setFont(new Font("Serif", Font.BOLD, 23));
		newcheckindateField.setBackground(new Color(255, 255, 255));
		newcheckindateField.setText("SELECT DATE");
		newcheckindateField.setOpaque(true);
		newcheckindateField.setBounds(267, 15, 105, 40);
		newcheckindateField.setColumns(10);
		newcheckindateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup DP = new DatePopup(newcheckindateField);
				DP.showDialog();
			}
		});
		// setting check out yyyy/mm/dd
		newcheckoutdateField.setEditable(true);
		newcheckoutdateField.setFont(new Font("Serif", Font.BOLD, 23));
		newcheckoutdateField.setBackground(new Color(255, 255, 255));
		newcheckoutdateField.setText("SELECT DATE");
		newcheckoutdateField.setOpaque(true);
		newcheckoutdateField.setBounds(267, 15, 105, 40);
		newcheckoutdateField.setColumns(10);
		newcheckoutdateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup DP = new DatePopup(newcheckoutdateField);
				DP.showDialog();
			}
		});
		// panel2 adding
		panel2.add(change2);
		panel2.add(newcheckindateField);
		panel2.add(to2);
		panel2.add(newcheckoutdateField);
		// set 'back' and 'next' button
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 3));
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		cancelrevisedate.setFont(new Font("Arial Black", Font.PLAIN, 20));
		backrevisedate.setFont(new Font("Arial Black", Font.PLAIN, 20));
		nextrevisedate.setFont(new Font("Arial Black", Font.PLAIN, 20));
		buttons.add(cancelrevisedate);
		buttons.add(backrevisedate);
		buttons.add(nextrevisedate);
		// revise date adding
		Revisedate.add(panel1);
		Revisedate.add(panel2);
		Revisedate.add(buttons);
	}

	// change room / revise date success
	private void initChangeRevisesuccess() {
		ChangeRevise_success.setLayout(new GridLayout(1, 1, 0, 0));
		ChangeRevise_success.setOpaque(false);
		changerevisesuccessText.setFont(new Font("Dialog", Font.PLAIN, 28));
		changerevisesuccessText.setForeground(new Color(70, 130, 180));
		ChangeRevise_success.add(changerevisesuccessText);
	}

	// change room error
	private void initChangeroomerror() {
		Changeroom_error.setLayout(new GridLayout(1, 1, 0, 0));
		Changeroom_error.setOpaque(false);
//		Changeroom_error.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		changeroomerrorText.setFont(new Font("Arial", Font.BOLD, 30));
		changeroomerrorText.setForeground(new Color(255, 0, 0));
		Changeroom_error.add(changeroomerrorText);
	}

    // revise date error
	private void initRevisedateerror() {
		Revisedate_error.setLayout(new GridLayout(1, 1, 0, 0));
		Revisedate_error.setOpaque(false);
//		Revisedate_error.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		revisedateerrorText.setFont(new Font("Arial", Font.BOLD, 30));
		revisedateerrorText.setForeground(new Color(255, 0, 0));
		Revisedate_error.add(revisedateerrorText);
	}

	// cancel order success
	private void initcancelordersuccess() {
		Cancelorder_success.setLayout(new GridLayout(2, 1, 0, 0));
		Cancelorder_success.setOpaque(false);
		Cancelorder_success.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		cancelordersuccessText.setFont(new Font("Dialog", Font.BOLD, 30));
		cancelordersuccessText.setForeground(new Color(70, 130, 180));
		confirmcancelordersuccess.setFont(new Font("Arial", Font.BOLD, 30));
		Cancelorder_success.add(cancelordersuccessText);
		Cancelorder_success.add(confirmcancelordersuccess);
	}

	// show the reserve information
	public void showReserveorder(int hid, int sroom, int droom, int qroom, String chkindate, String chkoutdate,
			int night, int p) {
		reserveorderhotelIDField.setText(Integer.toString(hid));
		reserveordersingleroomField.setText(Integer.toString(sroom));
		reserveorderdoubleroomField.setText(Integer.toString(droom));
		reserveorderquadroomField.setText(Integer.toString(qroom));
		reserveordercheckindateField.setText(chkindate);
		reserveordercheckoutdateField.setText(chkoutdate);
		reserveorderstaynightField.setText(Integer.toString(night));
		reserveorderpriceField.setText(Integer.toString(p));
	}

	// show reserve success
	public void showreservesuccess(int OrderID, int hid, int sroom, int droom, int qroom, String chkindate,
			String chkoutdate, int night, int p) {
		successreservenumberField.setText(Integer.toString(OrderID));
		layeredPane.add(Reserve_success, new Integer(3));
		showReserveorder(hid, sroom, droom, qroom, chkindate, chkoutdate, night, p);
		layeredPane.add(Reserveorder, new Integer(3));
		reservenumberField.setText(null);
		reservecheckindateField.setText("SELECT DATE");
		reservecheckoutdateField.setText("SELECT DATE");
		reservesingleroomField.setText(null);
		reservedoubleroomField.setText(null);
		reservequadroomField.setText(null);
	}

	// clear all the Text when logout
	public void clearalltext() {
		signinidField.setText(null);
		signinpasswordField.setText(null);
		signupidField.setText(null);
		signuppasswordField.setText(null);
		usercodeField.setText(null);
		entercheckindateField.setText("SELECT DATE");
		entercheckoutdateField.setText("SELECT DATE");
		enterpeopleField.setText(null);
		enterroomField.setText(null);
		reserveorderhotelIDField.setText(null);
		reserveordersingleroomField.setText(null);
		reserveorderdoubleroomField.setText(null);
		reserveorderquadroomField.setText(null);
		reserveordercheckindateField.setText(null);
		reserveordercheckoutdateField.setText(null);
		reserveorderstaynightField.setText(null);
		reserveorderpriceField.setText(null);
		reservenumberField.setText(null);
		reservecheckindateField.setText("SELECT DATE");
		reservecheckoutdateField.setText("SELECT DATE");
		reservesingleroomField.setText(null);
		reservedoubleroomField.setText(null);
		reservequadroomField.setText(null);
		String[] option = new String[1500];
		for (Integer i = 0; i < 1500; i++) {
			option[i] = i.toString();
		}
		reservehotelid = new JComboBox<Object>(option);
	}

	// make hotel list 建立Table
	// 但取到價錢似乎怪怪的
	public DefaultTableModel makeHotellist(ArrayList<AvailableHotelRooms> _AHR) {
		DefaultTableModel tablemodel = new DefaultTableModel(heading, 0);
		// get data
		for (int i = 0; i < _AHR.size(); i++) {
			int id = _AHR.get(i).getHotelID(); // id
			int star = _AHR.get(i).getHotelStar(); // star
			String locality = _AHR.get(i).getLocality(); // locality
			String address = _AHR.get(i).getAddress(); // address
			int sroom = _AHR.get(i).getSingle(); // single room
			int droom = _AHR.get(i).getDouble(); // double room
			int qroom = _AHR.get(i).getQuad(); // quad room
			int price = main.CountSumPrice(_AHR.get(i)); // price
			Object[] data = { id, star, locality, address, sroom, droom, qroom, price };
			tablemodel.addRow(data);
		}

		return tablemodel;
	}

	
	// show hotel list 設定外觀
	public void showHotellist(DefaultTableModel tablemodel) {
		HotellistTable = new JTable(tablemodel);

		HotellistTable.setEnabled(false);
		JTableHeader head = HotellistTable.getTableHeader();
		head.setFont(new Font("Arial", Font.PLAIN, 20));
		HotellistTable.setRowHeight(40); // row height
		// column width
		HotellistTable.getColumnModel().getColumn(0).setMaxWidth(60); // id
		HotellistTable.getColumnModel().getColumn(1).setMaxWidth(50); // star
		HotellistTable.getColumnModel().getColumn(2).setMaxWidth(60); // locality
		HotellistTable.getColumnModel().getColumn(3).setMaxWidth(300); // address
		HotellistTable.getColumnModel().getColumn(4).setMaxWidth(70); // single room
		HotellistTable.getColumnModel().getColumn(5).setMaxWidth(70); // double room
		HotellistTable.getColumnModel().getColumn(6).setMaxWidth(70); // quad room
		HotellistTable.getColumnModel().getColumn(7).setMaxWidth(70); // price
		// row color
		DefaultTableCellRenderer ter = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (row % 2 == 0)
					setBackground(new Color(248, 248, 255));
				else if (row % 2 == 1)
					setBackground(Color.white);
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		};
		for (int i = 0; i <= 7; i++) {
			HotellistTable.getColumn(heading[i]).setCellRenderer(ter);
		}
		// build up Table
		JScrollPane HotellistJScrollPane = new JScrollPane(HotellistTable,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// set 'back' and 'next' button
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		backhotellist.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reservehotellist.setFont(new Font("Arial Black", Font.PLAIN, 20));
		buttons.add(backhotellist);
		buttons.add(reservehotellist);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.setOpaque(false);
		JPanel panel_ = new JPanel();
		panel_.setOpaque(false);
		panel_.add(pricehighText);
		panel_.add(pricelowText);
		panel.add(star);
		panel.add(panel_);

		Hotellist.removeAll();
		Hotellist.add(panel, BorderLayout.NORTH);
		Hotellist.add(HotellistJScrollPane, BorderLayout.CENTER);
		Hotellist.add(buttons, BorderLayout.SOUTH);
	}

	// sub menu
	private void initSubMenu() {
		subMenu.setLayout(new GridLayout(1, 2, 0, 0));
		subMenu.setOpaque(false);
		subMenu.setBackground(null);
		subMenu.add(signinText);
		subMenu.add(signupText);
//		subMenu.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
	}

	private void initLayerPane() {
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(frameWidth, frameHeight));

		this.background.setIcon(new ImageIcon("images/Menu/hotelbackground.jpg"));
		this.background.setBounds(0, 0, frameWidth, frameHeight);
		layeredPane.add(background, new Integer(0));
		
		this.title.setBounds(titleCenter.width - (titleWidth / 2), titleCenter.height - (titleHeight / 2), titleWidth,
				titleHeight);
		layeredPane.add(title, new Integer(1));

		this.subMenu.setBounds(subMenuCenter.width - (subMenuWidth / 2), subMenuCenter.height - (subMenuHeight / 2),
				subMenuWidth, subMenuHeight);
		layeredPane.add(subMenu, new Integer(2));

		this.add(layeredPane);

		this.Signin.setBounds(signinSetCenter.width - (signinSetWidth / 2),
				signinSetCenter.height - (signinSetHeight / 2), signinSetWidth, signinSetHeight);

		this.Signup.setBounds(signupSetCenter.width - (signupSetWidth / 2),
				signupSetCenter.height - (signupSetHeight / 2), signupSetWidth, signupSetHeight);

		this.Signinerror.setBounds(signinerrorCenter.width - (signinerrorWidth / 2),
				signinerrorCenter.height - (signinerrorHeight / 2), signinerrorWidth, signinerrorHeight);

		this.Signinerror1.setBounds(signinerror1Center.width - (signinerror1Width / 2),
				signinerror1Center.height - (signinerror1Height / 2), signinerror1Width, signinerror1Height);

		this.Signuperror.setBounds(signuperrorCenter.width - (signuperrorWidth / 2),
				signuperrorCenter.height - (signuperrorHeight / 2), signuperrorWidth, signuperrorHeight);

		this.Signuperror1.setBounds(signuperrorCenter1.width - (signuperror1Width / 2),
				signuperrorCenter1.height - (signuperror1Height / 2), signuperror1Width, signuperror1Height);

		this.Hotelfunction.setBounds(hotelfunctionCenter.width - (hotelfunctionWidth / 2),
				hotelfunctionCenter.height - (hotelfunctionHeight / 2), hotelfunctionWidth, hotelfunctionHeight);

		this.EnterSearch.setBounds(entersearchCenter.width - (entersearchWidth / 2),
				entersearchCenter.height - (entersearchlistHeight / 2), entersearchWidth, entersearchlistHeight);

		this.Hotellist.setBounds(hotellistCenter.width - (hotellistWidth / 2),
				hotellistCenter.height - (hotellistHeight / 2), hotellistWidth, hotellistHeight);

		this.Invalid_date_error.setBounds(invaliddateerrorCenter.width - (invaiddateerrorWidth / 2),
				invaliddateerrorCenter.height - (invaliddateerrorHeight / 2), invaiddateerrorWidth,
				invaliddateerrorHeight);

		this.No_matched_hotel_error.setBounds(nomatchedhotelerrorCenter.width - (nomatchedhotelerrorWidth / 2),
				nomatchedhotelerrorCenter.height - (nomatchedhotelerrorHeight / 2), nomatchedhotelerrorWidth,
				nomatchedhotelerrorHeight);

		this.Search.setBounds(searchCenter.width - (searchWidth / 2), searchCenter.height - (searchHeight / 2),
				searchWidth, searchHeight);

		this.Reserve.setBounds(reserveCenter.width - (reserveWidth / 2), reserveCenter.height - (reserveHeight / 2),
				reserveWidth, reserveHeight);

		this.Reserve_success.setBounds(reservesuccessCenter.width - (reservesuccessWidth / 2),
				reservesuccessCenter.height - (reservesuccessHeight / 2), reservesuccessWidth, reservesuccessHeight);

		this.Soldout.setBounds(soldoutCenter.width - (soldoutWidth / 2), soldoutCenter.height - (soldoutHeight / 2),
				soldoutWidth, soldoutHeight);

		this.Inquiry.setBounds(InquiryCenter.width - (InquiryWidth / 2), InquiryCenter.height - (InquiryHeight / 2),
				InquiryWidth, InquiryHeight);

		this.Wrong_reservation_number.setBounds(wrongreservationnumberCenter.width - (wrongreservationnumberWidth / 2),
				wrongreservationnumberCenter.height - (wrongreservationnumberHeight / 2), wrongreservationnumberWidth,
				wrongreservationnumberHeight);

		this.Reserveorder.setBounds(reserveorderCenter.width - (reserveorderWidth / 2),
				reserveorderCenter.height - (reserveorderHeight / 2), reserveorderWidth, reserveorderHeight);

		this.Modify.setBounds(modifyCenter.width - (modifyWidth / 2), modifyCenter.height - (modifyHeight / 2),
				modifyWidth, modifyHeight);

		this.Changeroom.setBounds(changeroomCenter.width - (changeroomWidth / 2),
				changeroomCenter.height - (changeroomHeight / 2), changeroomWidth, changeroomHeight);

		this.Changeroom_error.setBounds(changeroomerrorCenter.width - (changeroomerrorWidth / 2),
				changeroomerrorCenter.height - (changeroomerrorHeight / 2), changeroomerrorWidth,
				changeroomerrorHeight);

		this.Revisedate.setBounds(revisedateCenter.width - (revisedateWidth / 2),
				revisedateCenter.height - (revisedateHeight / 2), revisedateWidth, revisedateHeight);

		this.Revisedate_error.setBounds(revisedateerrorCenter.width - (revisedateerrorWidth / 2),
				revisedateerrorCenter.height - (revisedateerrorHeight / 2), revisedateerrorWidth,
				revisedateerrorHeight);
		
		this.ChangeRevise_success.setBounds(changerevisesuccessCenter.width - (changerevisesuccessWidth / 2),
				changerevisesuccessCenter.height - (changerevisesuccessHeight / 2), changerevisesuccessWidth,
				changerevisesuccessHeight);

		this.Cancelorder_success.setBounds(cancelordersuccessCenter.width - (cancelordersuccessWidth / 2),
				cancelordersuccessCenter.height - (cancelordersuccessHeight / 2), cancelordersuccessWidth,
				cancelordersuccessHeight);

	}

	public Menu() {
		initPanel();
		initTitle();
		initSubMenu();
		initSignIn();
		initSignUp();
		initHotelfunction();
		initEnterSearch();
		initSearch();
		initReserve();
		initInquiry();
		initReserveorder();
		initLayerPane();
		initSigninerror();
		initSigninerror1();
		initSignuperror();
		initSignuperror1();
		initEnterinvaliddateerror();
		initNomatchedhotelerror();
		initWrongreservationnumber();
		initSoldout();
		initReservesuccess();
		initHotellist();
		initModify();
		initChangeroom();
		initRevisedate();
		initChangeRevisesuccess();
		initcancelordersuccess();
		initChangeroomerror();
		initRevisedateerror();
		// buttons in sub menu / sign in / sign up
		signinText.addMouseListener(ml);
		signupText.addMouseListener(ml);
		signinback.addMouseListener(ml);
		signinlogin.addMouseListener(ml);
		signupcancel.addMouseListener(ml);
		signuplogin.addMouseListener(ml);
		// buttons of sign in sign up error
		backsigninerror.addMouseListener(ml);
		backsigninerror1.addMouseListener(ml);
		backsignuperror.addMouseListener(ml);
		// buttons in hotel function
		searchText.addMouseListener(ml);
		reserveText.addMouseListener(ml);
		inquiryText.addMouseListener(ml);
		logout.addMouseListener(ml);
		// buttons in enter hotel list
		backentersearch.addMouseListener(ml);
		nextentersearch.addMouseListener(ml);
		// buttons in no matched hotel error
		backnomatchedhotelerror.addMouseListener(ml);
		// buttons in search
		star5.addMouseListener(ml);
		star4.addMouseListener(ml);
		star3.addMouseListener(ml);
		star2.addMouseListener(ml);
		pricehighText.addMouseListener(ml);
		pricelowText.addMouseListener(ml);
		backsearch.addMouseListener(ml);
		// buttons in hotel list
		backhotellist.addMouseListener(ml);
		reservehotellist.addMouseListener(ml);
		// buttons in reserve
		cancelreserve.addMouseListener(ml);
		backreserve.addMouseListener(ml);
		nextreserve.addMouseListener(ml);
		// buttons in sold out
		backsoldout.addMouseListener(ml);
		// buttons in inquiry
		backinquiry.addMouseListener(ml);
		nextinquiry.addMouseListener(ml);
		// buttons in wrong reservation number
		backwrongreservationnumber.addMouseListener(ml);
		// buttons in modify and cancel reservation
		modifyText.addMouseListener(ml);
		cancelText.addMouseListener(ml);
		confirmText.addMouseListener(ml);
		// buttons in modify
		changeroomText.addMouseListener(ml);
		revisedateText.addMouseListener(ml);
		// buttons in reduce room
		cancelchangeroom.addMouseListener(ml);
		backchangeroom.addMouseListener(ml);
		nextchangeroom.addMouseListener(ml);
		// buttons in revise date
		cancelrevisedate.addMouseListener(ml);
		backrevisedate.addMouseListener(ml);
		nextrevisedate.addMouseListener(ml);
		// buttons in cancel order succsee
		confirmcancelordersuccess.addMouseListener(ml);
	};

	MouseListener ml = new MouseAdapter() {
		public void mouseEntered(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			l.setForeground(Color.red);
		}

		public void mouseExited(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			l.setForeground(Color.black);
		}

		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == signupText) {
				layeredPane.remove(subMenu);
				verifycodeField.setText(main.getRandomString(6));
				layeredPane.add(Signup, new Integer(2));
				validate();
				repaint();
				signupText.setForeground(Color.black);
			} else if (e.getSource() == signupcancel) {
				layeredPane.remove(Signup);
				layeredPane.add(subMenu, new Integer(2));
				validate();
				repaint();
				signupcancel.setForeground(Color.black);
			} else if (e.getSource() == signuplogin) {
				String UserID = signupidField.getText();
				String Password = signuppasswordField.getText();
				String UserCode = usercodeField.getText(); // user enter verify code
				String VerifyCode = verifycodeField.getText(); // random verify code
				if (main.SignUpCheck(UserID, Password, UserCode)) {

					if (UserCode.equals(VerifyCode)) {
						// Create a new User
						main.user = new User(UserID, Password);
						main.UserList.add(main.user);

						layeredPane.remove(Signup);
						layeredPane.remove(title);
						layeredPane.add(Hotelfunction, new Integer(3));
						validate();
						repaint();
						signuplogin.setForeground(Color.black);
					} else {// Wrong verify code.
						System.out.println("hihi");
						layeredPane.remove(Signup);
						layeredPane.add(Signuperror1, new Integer(3));
						signinidField.setText("");
						signinpasswordField.setText("");
						verifycodeField.setText(main.getRandomString(6));
						validate();
						repaint();
						signuplogin.setForeground(Color.black);
					}
				} else {// UserID already existed.
					layeredPane.remove(Signup);
					layeredPane.add(Signuperror, new Integer(3));
					signupidField.setText("");
					signuppasswordField.setText("");
					verifycodeField.setText(main.getRandomString(6));
					validate();
					repaint();
					signuplogin.setForeground(Color.black);
				}
			} else if (e.getSource() == signinText) {
				layeredPane.remove(subMenu);
				layeredPane.add(Signin, new Integer(2));
				validate();
				repaint();
				signinText.setForeground(Color.black);
			} else if (e.getSource() == signinback) {
				layeredPane.remove(Signin);
				layeredPane.add(subMenu, new Integer(2));
				validate();
				repaint();
				signinback.setForeground(Color.black);
			} else if (e.getSource() == signinlogin) {
				String UserID = signinidField.getText();
				String Password = signinpasswordField.getText();
				int re = main.SignInCheck(UserID, Password);
				if (re == 1) {
					layeredPane.remove(Signin);
					layeredPane.remove(title);
					layeredPane.add(Hotelfunction, new Integer(2));
					validate();
					repaint();
					signinlogin.setForeground(Color.black);
				} else if (re == 0) {
					// UserID doesn't exist.
					layeredPane.remove(Signin);
					layeredPane.add(Signinerror, new Integer(2));
					signinidField.setText("");
					signinpasswordField.setText("");
					validate();
					repaint();
					signinlogin.setForeground(Color.black);
				} else if (re == -1) {
					// Wrong Password.
					layeredPane.remove(Signin);
					layeredPane.add(Signinerror1, new Integer(2));
					signinidField.setText("");
					signinpasswordField.setText("");
					validate();
					repaint();
					signinlogin.setForeground(Color.black);
				}
			} else if (e.getSource() == backsigninerror || e.getSource() == backsigninerror1) {
				layeredPane.remove(Signinerror);
				layeredPane.remove(Signinerror1);
				layeredPane.add(Signin);
				signinidField.setText(null);
				signinpasswordField.setText(null);
				validate();
				repaint();
				backsigninerror.setForeground(Color.black);
				backsigninerror1.setForeground(Color.black);
			} else if (e.getSource() == backsignuperror || e.getSource() == backsignuperror1) {
				layeredPane.remove(Signuperror);
				layeredPane.remove(Signuperror1);
				layeredPane.add(Signup);
				signupidField.setText(null);
				signuppasswordField.setText(null);
				usercodeField.setText(null);
				validate();
				repaint();
				backsignuperror.setForeground(Color.black);
				backsignuperror1.setForeground(Color.black);
			} else if (e.getSource() == logout) {
//這裡怪怪的 signin/signup error應該不用跑到最首頁吧？
// 修好了！ 
				layeredPane.remove(Hotelfunction);
				layeredPane.remove(Signin);
				layeredPane.remove(Signup);
				layeredPane.add(title, new Integer(1));
				layeredPane.add(subMenu, new Integer(2));
				validate();
				repaint();
				logout.setForeground(Color.black);
				// clear all the text field
				clearalltext();
			} else if (e.getSource() == searchText) {
				layeredPane.remove(Hotelfunction);
				layeredPane.add(EnterSearch, new Integer(3));
				validate();
				repaint();
				searchText.setForeground(Color.black);
			} else if (e.getSource() == nextentersearch) {
				initSearch();
				String s1 = entercheckindateField.getText();
				String s2 = entercheckoutdateField.getText();
//這裡看看能不能直接在選日期的時候限定好
// 我現在想到的方法是：不要一開始就傳給CID COD。先檢查 s1 s2，對的話再傳進去，不對就不要傳。
				if (main.CountDaysBetween(s1, s2) > 0) {
					String CID = entercheckindateField.getText();
					String COD = entercheckoutdateField.getText();
					int People = Integer.parseInt(enterpeopleField.getText());
					int Rooms = Integer.parseInt(enterroomField.getText());
					ArrayList<AvailableHotelRooms> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
					if (AHR.size() > 0) {
						layeredPane.remove(EnterSearch);
						layeredPane.remove(Invalid_date_error);
						layeredPane.add(Search, new Integer(3));
						reservebuttons.removeAll();
						reservebuttons.add(cancelreserve);
						reservebuttons.add(backreserve);
						reservebuttons.add(nextreserve);
						validate();
						repaint();
						nextentersearch.setForeground(Color.black);
					} else {
						// no matched hotel
						layeredPane.remove(EnterSearch);
						layeredPane.remove(Invalid_date_error);
						layeredPane.add(No_matched_hotel_error, new Integer(3));
						validate();
						repaint();
						nextentersearch.setForeground(Color.black);
					}
				} else {// Invaid Date
					layeredPane.add(Invalid_date_error, new Integer(3));
					entercheckindateField.setText("SELECT DATE");
					entercheckoutdateField.setText("SELECT DATE");
					enterpeopleField.setText(null);
					enterroomField.setText(null);
					validate();
					repaint();
					nextentersearch.setForeground(Color.black);
				}
			} else if(e.getSource() == pricehighText) { // show price high first
				layeredPane.remove(Search);
				layeredPane.remove(Hotellist);
				initSearch();
				
				String CID = entercheckindateField.getText();
				String COD = entercheckoutdateField.getText();
				int People = Integer.parseInt(enterpeopleField.getText());
				int Rooms = Integer.parseInt(enterroomField.getText());
				ArrayList<AvailableHotelRooms> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
				ArrayList<AvailableHotelRooms> nAHR = main.SortByPrice(AHR, 0);
				DefaultTableModel dtm = makeHotellist(nAHR);
				showHotellist(dtm);
				
				layeredPane.add(Hotellist, new Integer(3));
				validate();
				repaint();
				pricehighText.setForeground(Color.black);	
			} else if(e.getSource() == pricelowText) { // show price low first
				layeredPane.remove(Search);
				layeredPane.remove(Hotellist);
				initSearch();
				
				String CID = entercheckindateField.getText();
				String COD = entercheckoutdateField.getText();
				int People = Integer.parseInt(enterpeopleField.getText());
				int Rooms = Integer.parseInt(enterroomField.getText());
				ArrayList<AvailableHotelRooms> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
				ArrayList<AvailableHotelRooms> nAHR = main.SortByPrice(AHR, 1);
				DefaultTableModel dtm = makeHotellist(nAHR);
				showHotellist(dtm);
				
				layeredPane.add(Hotellist, new Integer(3));
				validate();
				repaint();
				pricelowText.setForeground(Color.black);
			} else if (e.getSource() == star5) { // show star 5 hotel
				layeredPane.remove(Search);
				layeredPane.remove(Hotellist);
				initSearch();

				String CID = entercheckindateField.getText();
				String COD = entercheckoutdateField.getText();
				int People = Integer.parseInt(enterpeopleField.getText());
				int Rooms = Integer.parseInt(enterroomField.getText());
				ArrayList<AvailableHotelRooms> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
				ArrayList<AvailableHotelRooms> nAHR = main.SearchByStar(AHR, 5);
				DefaultTableModel dtm = makeHotellist(nAHR);
				showHotellist(dtm);

				layeredPane.add(Hotellist, new Integer(3));
				validate();
				repaint();
				star5.setForeground(Color.black);
			} else if (e.getSource() == star4) { // show star 4 hotel
				layeredPane.remove(Search);
				layeredPane.remove(Hotellist);
				initSearch();

				String CID = entercheckindateField.getText();
				String COD = entercheckoutdateField.getText();
				int People = Integer.parseInt(enterpeopleField.getText());
				int Rooms = Integer.parseInt(enterroomField.getText());
				ArrayList<AvailableHotelRooms> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
				ArrayList<AvailableHotelRooms> nAHR = main.SearchByStar(AHR, 4);
				DefaultTableModel dtm = makeHotellist(nAHR);
				showHotellist(dtm);
				
				layeredPane.remove(Search);
				layeredPane.add(Hotellist, new Integer(3));
				validate();
				repaint();
				star4.setForeground(Color.black);
			} else if (e.getSource() == star3) { // show star 3 hotel
				layeredPane.remove(Search);
				layeredPane.remove(Hotellist);
				initSearch();

				String CID = entercheckindateField.getText();
				String COD = entercheckoutdateField.getText();
				int People = Integer.parseInt(enterpeopleField.getText());
				int Rooms = Integer.parseInt(enterroomField.getText());
				ArrayList<AvailableHotelRooms> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
				ArrayList<AvailableHotelRooms> nAHR = main.SearchByStar(AHR, 3);
				DefaultTableModel dtm = makeHotellist(nAHR);
				showHotellist(dtm);

				layeredPane.add(Hotellist, new Integer(3));
				validate();
				repaint();
				star3.setForeground(Color.black);
			} else if (e.getSource() == star2) { // show star 2 hotel
				layeredPane.remove(Search);
				layeredPane.remove(Hotellist);
				initSearch();
				String CID = entercheckindateField.getText();
				String COD = entercheckoutdateField.getText();
				int People = Integer.parseInt(enterpeopleField.getText());
				int Rooms = Integer.parseInt(enterroomField.getText());
				ArrayList<AvailableHotelRooms> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
				ArrayList<AvailableHotelRooms> nAHR = main.SearchByStar(AHR, 2);
				DefaultTableModel dtm = makeHotellist(nAHR);
				showHotellist(dtm);

				layeredPane.add(Hotellist, new Integer(2));
				validate();
				repaint();
				star2.setForeground(Color.black);
			} else if (e.getSource() == backhotellist) {
				initSearch();
				layeredPane.remove(Hotellist);
				layeredPane.add(EnterSearch);
				validate();
				repaint();
				backhotellist.setForeground(Color.black);
			} else if (e.getSource() == reservehotellist) {
				reservecheckindateField.setText(entercheckindateField.getText());
				reservecheckoutdateField.setText(entercheckoutdateField.getText());
				layeredPane.remove(Hotellist);
				layeredPane.add(Reserve, new Integer(3));
				validate();
				repaint();
				reservehotellist.setForeground(Color.black);
			} else if (e.getSource() == backsearch) {
				layeredPane.remove(Search);
				layeredPane.add(EnterSearch, new Integer(3));
				validate();
				repaint();
				backsearch.setForeground(Color.black);
			} else if (e.getSource() == backnomatchedhotelerror) {
				layeredPane.remove(No_matched_hotel_error);
				layeredPane.add(EnterSearch, new Integer(3));
				entercheckindateField.setText("SELECT DATE");
				entercheckoutdateField.setText("SELECT DATE");
				enterpeopleField.setText(null);
				enterroomField.setText(null);
				validate();
				repaint();
				backnomatchedhotelerror.setForeground(Color.black);
			} else if (e.getSource() == backentersearch || e.getSource() == backinquiry
					|| e.getSource() == cancelreserve || e.getSource() == cancelchangeroom
					|| e.getSource() == cancelrevisedate) {
				layeredPane.remove(EnterSearch);
				layeredPane.remove(Inquiry);
				layeredPane.remove(Reserve);
				layeredPane.remove(Reserveorder);
				layeredPane.remove(Reserve_success);
				layeredPane.remove(Invalid_date_error);
				layeredPane.remove(Changeroom);
				layeredPane.remove(Revisedate);
				layeredPane.remove(Changeroom_error);
				layeredPane.remove(Revisedate_error);
				reservebuttons.removeAll();
				reservebuttons.add(cancelreserve);
				reservebuttons.add(nextreserve);
				layeredPane.add(Hotelfunction);
				validate();
				repaint();
				backentersearch.setForeground(Color.black);
				backinquiry.setForeground(Color.black);
				confirmText.setForeground(Color.black);
				cancelreserve.setForeground(Color.black);
				cancelchangeroom.setForeground(Color.black);
				cancelrevisedate.setForeground(Color.black);
			} else if (e.getSource() == confirmText || e.getSource() == confirmcancelordersuccess) {
				layeredPane.remove(Reserveorder);
				layeredPane.remove(Reserve_success);
				layeredPane.remove(ChangeRevise_success);
				layeredPane.remove(Cancelorder_success);
				
				entercheckindateField.setText("SELECT DATE");
				entercheckoutdateField.setText("SELECT DATE");
				enterpeopleField.setText(null);
				enterroomField.setText(null);

				reservenumberField.setText(null);
				successreservenumberField.setText(null);

				reservebuttons.removeAll();
				reservebuttons.add(cancelreserve);
				reservebuttons.add(nextreserve);

				reserveorderbuttons.removeAll();
				reserveorderbuttons.add(cancelText);
				reserveorderbuttons.add(modifyText);
				reserveorderbuttons.add(confirmText);
				
				layeredPane.add(Hotelfunction);
				validate();
				repaint();
				confirmText.setForeground(Color.black);
			} else if (e.getSource() == reserveText) {
				reservecheckindateField.setText("SELECT DATE");
				reservecheckoutdateField.setText("SELECT DATE");
				reservesingleroomField.setText(null);
				reservedoubleroomField.setText(null);
				reservequadroomField.setText(null);
				layeredPane.remove(Hotelfunction);
				layeredPane.add(Reserve, new Integer(3));
				validate();
				repaint();
				reserveText.setForeground(Color.black);
			} else if (e.getSource() == backreserve) {
				layeredPane.remove(Reserve);
				layeredPane.add(Hotellist);
				validate();
				repaint();
				backreserve.setForeground(Color.black);
			} else if (e.getSource() == nextreserve) {
				String s1 = reservecheckindateField.getText();
				String s2 = reservecheckoutdateField.getText();
				if (main.CountDaysBetween(s1, s2) > 0) {
					String CID = reservecheckindateField.getText();// yyyy/mm/dd
					String COD = reservecheckoutdateField.getText();
					int HotelID = reservehotelid.getSelectedIndex();
					int sn = Integer.parseInt(reservesingleroomField.getText());
					int dn = Integer.parseInt(reservedoubleroomField.getText());
					int qn = Integer.parseInt(reservequadroomField.getText());
					Order order = main.BookHotel(CID, COD, HotelID, sn, dn, qn);
					if (order != null) {
						// 訂房成功
						layeredPane.remove(Reserve);
						layeredPane.remove(Invalid_date_error);
						showreservesuccess(order.getID(), order.getHotelID(), order.getsn(), order.getdn(),
								order.getqn(), order.getCheckInDate(), order.getCheckOutDate(),
								(int) main.CountDaysBetween(order.getCheckInDate(), order.getCheckOutDate()),
								order.getSumPrice());
						reservenumberField.setText(successreservenumberField.getText());
						reservecheckindateField.setText(null);
						reservecheckoutdateField.setText(null);
						reservebuttons.removeAll();
						reservebuttons.add(cancelreserve);
						reservebuttons.add(nextreserve);
						validate();
						repaint();
						nextreserve.setForeground(Color.black);
					} else {
						// 訂房失敗 房間數量不足/房間已售罄
						layeredPane.remove(Reserve);
						layeredPane.remove(Invalid_date_error);
						layeredPane.add(Soldout, new Integer(3));
						validate();
						repaint();
						nextreserve.setForeground(Color.black);
					}
				} else { // reserve invalid date
					layeredPane.add(Invalid_date_error, new Integer(3));
					reservecheckindateField.setText("SELECT DATE");
					reservecheckoutdateField.setText("SELECT DATE");
					reservesingleroomField.setText(null);
					reservedoubleroomField.setText(null);
					reservequadroomField.setText(null);
					validate();
					repaint();
					nextreserve.setForeground(Color.black);
				}
				
			} else if (e.getSource() == backsoldout) {
				layeredPane.remove(Soldout);
				layeredPane.remove(Invalid_date_error);
				layeredPane.add(Reserve, new Integer(3));
				validate();
				repaint();
				backsoldout.setForeground(Color.black);
			} else if (e.getSource() == inquiryText) {
				reservenumberField.setText(null);
				layeredPane.remove(Hotelfunction);
				layeredPane.add(Inquiry, new Integer(3));
				validate();
				repaint();
				inquiryText.setForeground(Color.black);
			} else if (e.getSource() == nextinquiry) {
				layeredPane.remove(Inquiry);
				// get reserve number
				int OrderID = Integer.parseInt(reservenumberField.getText());

				if (main.CheckOrder(OrderID) != null) {// unsolved bug : NPE
					Order order = main.CheckOrder(OrderID);
					System.out.println("here" + order != null);
					layeredPane.remove(Inquiry);
					showReserveorder(order.getHotelID(), order.getsn(), order.getdn(), order.getqn(),
							order.getCheckInDate(), order.getCheckOutDate(),
							(int) main.CountDaysBetween(order.getCheckInDate(), order.getCheckOutDate()),
							order.getSumPrice());
					layeredPane.add(Reserveorder, new Integer(3));
					validate();
					repaint();
					nextinquiry.setForeground(Color.black);
				} else {// 不存在此訂單代號
					layeredPane.add(Wrong_reservation_number, new Integer(3));
					validate();
					repaint();
					reservenumberField.setText("");
					nextinquiry.setForeground(Color.black);
				}
			} else if (e.getSource() == backwrongreservationnumber) {
				layeredPane.remove(Wrong_reservation_number);
				reservenumberField.setText(null);
				layeredPane.add(Inquiry, new Integer(3));
				validate();
				repaint();
				backwrongreservationnumber.setForeground(Color.black);
			} else if (e.getSource() == modifyText || e.getSource() == backchangeroom
					|| e.getSource() == backrevisedate) {
				layeredPane.remove(Reserve_success);
				layeredPane.remove(Reserveorder);
				layeredPane.remove(Changeroom);
				layeredPane.remove(Revisedate);
				layeredPane.remove(Changeroom_error);
				layeredPane.remove(Revisedate_error);
				layeredPane.remove(Invalid_date_error);
				layeredPane.add(Modify, new Integer(3));
				validate();
				repaint();
				modifyText.setForeground(Color.black);
				backchangeroom.setForeground(Color.black);
				backrevisedate.setForeground(Color.black);
			} else if (e.getSource() == changeroomText) {
				Order originorder = main.CheckOrder(Integer.parseInt(reservenumberField.getText()));
				Integer x = originorder.getsn();
				Integer y = originorder.getdn();
				Integer z = originorder.getqn();
				originsingleroomField.setText(x.toString());
				origindoubleroomField.setText(y.toString());
				originquadroomField.setText(z.toString());
				newsingleroomField.setText(null);
				newdoubleroomField.setText(null);
				newquadroomField.setText(null);
				layeredPane.remove(Modify);
				layeredPane.add(Changeroom, new Integer(3));
				validate();
				repaint();
				changeroomText.setForeground(Color.black);
			} else if (e.getSource() == nextchangeroom) {
				// 取得修改後的房間數
				int x = Integer.parseInt(newsingleroomField.getText());
				int y = Integer.parseInt(newdoubleroomField.getText());
				int z = Integer.parseInt(newquadroomField.getText());

//				// if change room success 修改房間數成功
//				showReserveorder(int hid, int sroom, int droom, int qroom, String chkindate, String chkoutdate, int night,
//						int p)
				layeredPane.remove(Changeroom); // if change room success
				layeredPane.remove(Changeroom_error); // if change room success
				layeredPane.add(ChangeRevise_success, new Integer(3));// if change room success
				reserveorderbuttons.remove(cancelText); // if change room success
				reserveorderbuttons.remove(modifyText); // if change room success
				layeredPane.add(Reserveorder, new Integer(3));// if change room success

				// if change room error  修改房間數失敗 (不可增加房間數量)
//				layeredPane.add(Changeroom_error, new Integer(3)); // if change room error
//				newsingleroomField.setText(null); // if change room error
//				newdoubleroomField.setText(null); // if change room error
//				newquadroomField.setText(null); // if change room error
				
				validate();
				repaint();
				nextchangeroom.setForeground(Color.black);
			} else if (e.getSource() == revisedateText) {
				Order originorder = main.CheckOrder(Integer.parseInt(reservenumberField.getText()));
				String x = originorder.getCheckInDate();
				String y = originorder.getCheckOutDate();
				origincheckindateField.setText(x);
				origincheckoutdateField.setText(y);
				newcheckindateField.setText(null);
				newcheckoutdateField.setText(null);
				layeredPane.remove(Modify);
				layeredPane.add(Revisedate, new Integer(3));
				validate();
				repaint();
				revisedateText.setForeground(Color.black);
			} else if (e.getSource() == nextrevisedate) {
				// 取得修改後的訂房日期
				String s1 = newcheckindateField.getText();
				String s2 = newcheckoutdateField.getText();
				if (main.CountDaysBetween(s1, s2) > 0) { // 檢查入住日期是否比退房日期之前
				
				// if revise date success 修改日期成功
//				showReserveorder(int hid, int sroom, int droom, int qroom, String chkindate, String chkoutdate, int night,
//				int p)
				layeredPane.remove(Invalid_date_error); // if revise date success
				layeredPane.remove(Revisedate_error); // if revise date success
				layeredPane.remove(Revisedate); // if revise date success
				layeredPane.add(ChangeRevise_success, new Integer(3)); // if revise date success
				reserveorderbuttons.remove(cancelText); // if revise date success
				reserveorderbuttons.remove(modifyText); // if revise date success
				layeredPane.add(Reserveorder, new Integer(3)); // if revise date success

				// if revise date error  修改日期失敗(不可延長住宿日期)
//				layeredPane.remove(Invalid_date_error); // if revise date error
//				layeredPane.add(Revisedate_error, new Integer(3)); // if revise date error
//				newcheckindateField.setText(null); // if revise date error
//				newcheckoutdateField.setText(null); // if revise date error
				
				validate();
				repaint();
				nextrevisedate.setForeground(Color.black);
				}
				else { // invalid date
					layeredPane.add(Invalid_date_error, new Integer(3));
					newcheckindateField.setText(null);
					newcheckoutdateField.setText(null);
					validate();
					repaint();
					nextrevisedate.setForeground(Color.black);
				}
			} else if (e.getSource() == cancelText) { // 取消訂單
				layeredPane.remove(Reserveorder);
				layeredPane.remove(Reserve_success);
				layeredPane.add(Cancelorder_success, new Integer(3));
				validate();
				repaint();
				cancelText.setForeground(Color.black);
			} 

		}
	};
}
