package com.example.library.controller;

import com.example.library.model.*;
import com.example.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService uService;
    @Autowired
    private AdminService adService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BorrowService borrowService;

    @Autowired
    private QishouService qishouService; //骑手管理

    @Autowired
    private QishouruzhangService qishouruzhangService; //骑手入账管理

    @Autowired
    private YonghuxinxiService yonghuxinxiService; //用户信息表

    @Autowired
    private YouhuiquanxinxiService youhuiquanxinxiService; //优惠券信息

    @Autowired
    private PeisongdizhiService peisongdizhiService; //配送地址



    @RequestMapping("/login")
    public String login(){

        return "admin/login";
    }

    @RequestMapping("/dologin")
    @ResponseBody
    public String dologin(@RequestParam("stuid") String stuid, @RequestParam("password")String password, @RequestParam("role")int role, HttpSession session){
        //System.out.println(role);
        if(role==2){
            String md5pwd = DigestUtils.md5DigestAsHex(password.getBytes());
            User u = uService.findUserByStuidAndPassword(stuid,md5pwd);
//            System.out.println(u);
            if(u!=null){
                session.setAttribute("username",u.getUsername());
                return "student";
            }
        }else{
            String md5pwd = DigestUtils.md5DigestAsHex(password.getBytes());
            Admin admin = adService.findAdminByStuidAndPassword(stuid,md5pwd);
//            System.out.println(admin);
            if(admin!=null){
                session.setAttribute("username",admin.getName());
                return "admin";
            }
        }
        return "false";
    }
  
    @RequestMapping("student")
    public String toStudent(){

        return "admin/student";
    }

    @RequestMapping("admin")
    public String toAdmin(){

        return "admin/toadmin";
    }
    //跳转到添加管理员页面
    @RequestMapping("addadmin")
    public String addAdmin(){

        return "admin/addadmin";
    }
   
    @RequestMapping("doaddadmin")
    public String doaddAdmin(Admin admin){
        admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
        adService.addAdmin(admin);
        return "admin/toadmin";
    }
    @RequestMapping("booklist")
    public String findAllBooks(Map map){
        List<Book> booklist = bookService.findAllBooks();
        map.put("booklist",booklist);
        return "admin/booklist";
    }

    @RequestMapping("delete")
    public String delete(@RequestParam("bid") int bid){
        bookService.deleteBookById(bid);
        return "redirect:booklist";
    }

    @RequestMapping("addbookUI")
    public String addbookUI(){

        return "admin/addBookUI";
    }
  
    @RequestMapping("addbook")
    @ResponseBody
    public String addbook(@RequestParam("bookname") String bookname, @RequestParam("author") String author, @RequestParam("type") String type, @RequestParam("publisher") String publisher, @RequestParam("publicationdate") String publicationdate, @RequestParam("price") int price, @RequestParam("state") String state, @RequestParam("comment") String comment) throws ParseException {
        Book book = new Book();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        String time = df.format(publicationdate);
//        df.parse(time)
        book.setPublicationdate(publicationdate);
        book.setBookname(bookname);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setType(type);
        book.setPrice(price);
        book.setState(state);
        book.setComment(comment);
//        System.out.println(book);
        int n =  bookService.addBook(book);
        if(n==1){
            return "success";
        }else{
            return "error";
        }
    }
    @RequestMapping("findAllStu")
    public String findAllStu(Map map){
        List<User> userlist = uService.findAllStu();
        map.put("userlist",userlist);
        return "admin/userlist";
    }
    @RequestMapping("allBorrowbooks")
    public String findallBorrowbooks(Map map){
        List<Borrow> allBorrowbooks= borrowService.findallBorrowbooks();
        map.put("allBorrowbooks",allBorrowbooks);
        return "admin/allBorrowbooks";
    }

  
    @RequestMapping("borrowbooklist")
    public String borrowbooklist(Map map){
        List<Book> booklist = bookService.findAllBooks();
        map.put("booklist",booklist);
        return "admin/borrowbooklist";
    }
    @RequestMapping("borrowbook")
    public String borrowbook(@RequestParam("bookname")String bookname ,@RequestParam("bid")int bid ,HttpSession session) throws ParseException {
        Borrow borrow = new Borrow();
        borrow.setBookname(bookname);
        borrow.setBid(bid);
        borrow.setBorrower(session.getAttribute("username").toString());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String time = df.format(System.currentTimeMillis());
        borrow.setBorrowtime(time);
        bookService.updateBookByBid(bid);
        return "redirect:borrowbooklist";
    }
    @RequestMapping("myBorrow")
    public String myborrow(Map map,HttpSession session){
        String username = session.getAttribute("username").toString();
        List<Borrow> borrowlist = borrowService.findAllMyBorrow(username);
        map.put("borrowlist",borrowlist);
        return "admin/myborrow";
    }
    @RequestMapping("returnbook")
    public String returnbook(@RequestParam("bid") int bid){
        //1.删除借阅表中bid=bid的记录
        borrowService.delMyBorrow(bid);
        //2.更新书籍表中的借阅状态
        bookService.upBookByBid(bid);
        return "redirect:myBorrow";
    }
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "admin/login";
    }
    @RequestMapping("toregist")
    public String toregist(){

        return "admin/regist";
    }
    @RequestMapping("regist")
    public String regist(User user){
        String md5pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5pwd);
        uService.addUser(user);
        return "redirect:login";
    }
    @RequestMapping("findAllQishou")
    public String findAllQishou(Map map){
        System.err.println("骑手管理。。。");
        List<Qishou> qishoulist = qishouService.findAllQishou();
        System.err.println("qishoulist："+qishoulist);
        map.put("qishoulist",qishoulist);
        return "admin/qishoulist";
    }

    @RequestMapping("findAllQishouruzhang")
    public String findAllQishouruzhang(Map map){
        List<Qishouruzhang> qishouruzhanglist = qishouruzhangService.findAllQishouruzhang();
        System.err.println("qishouruzhanglist："+qishouruzhanglist);
        map.put("qishouruzhanglist",qishouruzhanglist);
        return "admin/qishouruzhanglist";
    }
    @RequestMapping("findAllYonghuxinxi")
    public String findAllYonghuxinxi(Map map){
        System.err.println("用户信息管理。。。");
        List<Yonghuxinxi> yonghuxinxilist = yonghuxinxiService.findAllYonghuxinxi();
        System.err.println("yonghuxinxilist："+yonghuxinxilist);
        map.put("yonghuxinxilist",yonghuxinxilist);
        return "admin/yonghuxinxilist";
    }

    @RequestMapping("findAllYouhuiquanxinxi")
    public String findAllYouhuiquanxinxi(Map map){
        List<Youhuiquanxinxi> youhuiquanxinxilist = youhuiquanxinxiService.findAllYouhuiquanxinxi();
        System.err.println("youhuiquanxinxilist："+youhuiquanxinxilist);
        map.put("youhuiquanxinxilist",youhuiquanxinxilist);
        return "admin/youhuiquanxinxilist";
    }

    @RequestMapping("findAllPeisongdizhi")
    public String findAllPeisongdizhi(Map map){
        List<Peisongdizhi> peisongdizhilist = peisongdizhiService.findAllPeisongdizhi();
        System.err.println("peisongdizhilist："+peisongdizhilist);
        map.put("peisongdizhilist",peisongdizhilist);
        return "admin/peisongdizhilist";
    }


}
