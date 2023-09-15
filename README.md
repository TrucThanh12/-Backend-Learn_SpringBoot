# Inversion Of Control là gì?
## Khái niệm cơ bản về Inversion Of Control
IoC là một nguyên tắc thiết kế phần mềm, nó đảo ngược quyền kiểm soát của ứng dụng. Trong truyền thống, các đối tượng xây dựng và quản lý các thực thể khác. Tuy nhiên, khi áp dụng IoC, việc xử lý các khối logic và quyết định được chuyển sang bên ngoài và được quản lý bởi IoC Container. IoC có trách nhiệm xây dựng và quản lý các đối tượng, thay vì đối tượng tự quản lý chính nó
### Ví dụ
![vidu1](https://i.imgur.com/n967KTe.png) <br>
Trong mô hình không sử dụng IoC, class A sẽ phải khởi tạo và điều khiển hai class B và C, bất cứ thay đổi nào ở class A cũng sẽ dẫn tới thay đổi ở class B và C. Một thay đổi sẽ khéo theo hàng loạt các thay đổi khác, làm giảm khả năng bảo trì của code<br>
<br>
![vidu2](https://i.imgur.com/5joJJFm.png) <br>
Trong mô hình sử dụng IoC, các class B và C sẽ độc lập với class A thông qua bên thứ ba, do đó các class không phụ thuộc lẫn nhau mà chỉ phụ thuộc vào interface. Điều này đồng nghĩa rằng sự thay đổi ở class cấp cao không ảnh hướng tới class cấp thấp hơn <br>

## Các thành phần chính trong Inversion Of Control
* **Trung tâm IoC (IoC Container)**: đây là thành phần chính trong IoC, nơi quản lý việc xây dựng và cấu hình các đối tượng. Nó cung cấp các phương thức để đăng ký các đối tượng và đảm bảo rằng các phụ thuộc giữa các đối tượng được giải quyết đúng cách
* **Đối tượng**: là các thành phần trong ứng dụng, nó được xây dựng và quản lý bởi trung tâm IoC
* **Khối logic**: là các phần của chương trình được sử dụng để thực hiện các nhiệm vụ cụ thể thông qua các đối tượng

## Ưu điểm và nhược điểm của Inversion Of Control
### Ưu điểm
* Giảm sự phụ thuộc: IoC giúp giảm sự phụ thuộc giữa các đối tượng, từ đó tăng tính tương tác và tái sử dụng trong mã nguồn
* Dễ bảo trì: Khi sử dụng IoC, việc bảo trì và mở rộng mã nguồn trở nên dễ dàng hơn
* Kiểm soát: Trung tâm IoC cung cấp khả năng kiểm soát các đối tượng và quá trình xây dựng
### Nhược điểm
* Hiệu suất: việc sử dụng trung tâm IoC có thể ảnh hưởng đến hiệu suất của ứng dụng

<br>

# Dependency Injection
Là một kỹ thuật trong đó một object cung cấp các dependencies của một object khác. 
Là một cách hiện thực Inversion of Control. Các module phụ thuộc(dependency) sẽ được inject vào module cấp cao.<br>
![Alt text](https://i.imgur.com/ZY7XJ9j.png)
<br>
Ta có ví dụ sau:
* Class MySqlDAO chuyên thực hiện truy vấn cơ sở dữ liệu MySQL của ứng dụng
* Bây giờ nếu muốn truy vấn tới cơ sở dữ liệu từ Mongo. Thì phải xóa khai báo MySqlDAO và thay bằng MongoDAO, sau đó muốn dùng lại MySqlDAO thì lại phải sửa và test lại nhiều lần 

Để hiểu Dependency Injection một cách đơn giản như sau:<br>
![Alt text](https://i.imgur.com/Gw5Ysva.png)
* Các module không giao tiếp trực tiếp với nhau, mà thông qua interface. Module cấp thấp sẽ implement interface, module cấp cao sẽ gọi module cấp thấp thông qua interface
    * Để giao tiếp với database, ta có interface AbstractDAO, các module cấp thấp là MongoDAO, MySqlDAO. Module cấp cao là Client sẽ chỉ sử dụng interface AbstractDAO
* Việc khởi tạo các module cấp thấp sẽ do DI Container thực hiện
    * Trong module Client, ta sẽ không khởi tạo AbstractDAO db = new MongoDAO(), việc này sẽ do DI Container thực hiện. Module Client sẽ không biết gì về module MongoDAO hay MySqlDAO.
* Việc module nào gắn với interface nào sẽ được config trong code
* DI được dùng để làm giảm sự phụ thuộc giữa các module, dễ dàng hơn trong việc thay đổi module, bảo trì code và testing
## Ví dụ
### Tạo 1 interface để khai báo các method giao tiếp với database
![Alt text](https://i.imgur.com/a7y0rSj.png)
### Tạo các class DAO tương ứng với từng loại database và implements các method của AbstractDAO
![Alt text](https://i.imgur.com/sIAaNZv.png) <br>
![Alt text](https://i.imgur.com/a19RLsy.png) <br>
![Alt text](https://i.imgur.com/ff5pLJI.png)
### file config.properites lưu thông tin config quyết định kết nối với database nào
![Alt text](https://i.imgur.com/XDNPECz.png)
### Class Client cần dùng đến DAO thì chúng ta sẽ khai báo AbstractDAO
![Alt text](https://i.imgur.com/XWEkMpX.png)
### Tạo 1 file FactoryDAO để quyết định đối tượng được tạo ra
![Alt text](https://i.imgur.com/a5iWUt4.png)
### Demo
![Alt text](https://i.imgur.com/MCaFIcw.png)

## Các phương pháp thực hiện Dependency Injection
* **Contructor Injection**: Các denpendency sẽ được truyền vào 1 class thông qua constructor của class đó. <br>
![Alt text](https://i.imgur.com/hkhHtwU.png)

* **Setter Injection**: Các dependency sẽ được truyền vào 1 class thông qua các hàm Setter/Getter<br>
![Alt text](https://i.imgur.com/MQBqDEg.png)

* **Public fields**: Các dependency sẽ được truyền vào 1 class một cách trực tiếp vào các public field. Cách này ít dc sử dụng<br>
![Alt text](https://i.imgur.com/sT5D3II.png)

# What is a Spring Bean?
Spring Bean là các object trong Spring Framework, được khởi tạo thông qua Spring Container. Bean đại diện cho các thành phần cơ bản của ứng dụng và được quản lý bởi Spring Container để đảm bảo quản lý vòng đời của chúng và cung cấp Dependency Injection cho các thành phần khác trong ứng dụng.
## Ví dụ
![Alt text](https://i.imgur.com/sLsiVaY.png) <br>
![Alt text](https://i.imgur.com/S7g4LLt.png)

# Spring Bean Scope
Scope của một bean là nơi định nghĩa vòng đời, và cách nó được khởi tạo và quản lý bới IoC trong một ngữ cảnh cụ thể. Spring có 6 kiểu Scope như sau:
## Singleton
* Khi một bean khai báo là Singleton thì Bean đó là duy nhất trong Spring IoC và được share cho tất cả các Beans khác nếu cần sử dụng nó. Như vậy chỉ cần tạo một Bean duy nhất và sử dụng cho toàn hệ thống.
* Ví dụ: có 1 Bean connect database thì chỉ tạo 1 lần duy nhất. Các bean khác muốn dùng thì nhúng vào chứ không phải có 10 Beans khác nhau dùng Bean Connect DB trong Spring IoC
* Scope mặc định khi một Bean được tạo ra là Singleton
* Định nghĩa Scope Singleton bằng XML<br>
![hi](https://i.imgur.com/cQCA4qN.png)
* Định nghĩa Scope Singletion bằng Java<br>
![hi](https://i.imgur.com/UI3apLx.png)
## Prototype
* Khác với Singleto Scope, Bean sẽ được tạo ra mỗi khi có một yêu cầu tạo Bean. Mỗi lần gọi tới Bean mà có Scope là Prototype thì nó sẽ tạo ra một đối tượng Bean trong Spring IoC container
* Định nghĩa Scope Prototype bằng XML<br>
![hi](https://i.imgur.com/0uACnRQ.png)
* Định nghĩa Scope Prototype bằng Java<br>
![hi](https://i.imgur.com/oIa9ohv.png)

## Request
* Spring Container sẽ tạo bean mới khi có một request từ người dùng. Sau khi Request xử lý xong thì bean sẽ bị xóa đi
## Session
* Scope Session sẽ chỉ tồn tại chừng nào Session ở HTTP. Nó sẽ bị xóa đi khỏi Spring IoC khi Session ở Web bị xóa hoặc hết hiệu lực
* Ví dụ, thông tin đăng nhập của người dùng có thể được lưu trữ trong 1 bean với session scope
## Application
* Application Scope được tạo một lần cho toàn bộ ứng dụng Web Application. 
## WebSocket
* WebSocket thường được sử dụng để truyền tài dữ liệu giữa máy khách và máy chủ trong thời gian thực, và việc quản lý trạng thái hoặc thông tin phiên làm việc thường được thực hiện theo cách tùy thuộc vào ứng dụng cụ thể
> Những Scope như Request, Session, Application và Websocket thì chỉ tồn tại ở những ứng dụng là Web Application

# Spring Bean Annotations
## @Component
* Nó được sử dụng để đánh dấu một class là một Spring Component. Spring sẽ quét và tạo bean từ class được đánh dấu bởi **@Component** và thêm chúng và container IoC để quản lý
* Khi đánh dấu 1 class bằng **@Component**, Spring sẽ tự động tạo 1 bean từ class đó và thêm vào container IoC
* Spring quét các package được chỉ định để tìm và tạo bean từ các class được đánh dấu bởi **@Component** --> tự động phát hiện các bean trong ứng dụng
### Ví dụ
![j](https://i.imgur.com/F1ek5yj.png) <br>
![i](https://i.imgur.com/wHxc7l4.png)

## @Controller
* Là nơi đại diện cho lớp Controller trong mô hình MVC
* Nó được sử dụng để đánh dấu một class là một Spring Component, đảm nhiệm vai trò của 1 bộ điều khiển
* Thường chúng ta sử dụng **@Controller** kết hợp với chú thích **@RequestMapping** cho các phương thức xử lý yêu cầu và **@ResponseBody** cho phép chuyển đổi đối tượng trả về thành phản hồi HttpResponse
### Ví dụ
![hi](https://i.imgur.com/i5Pue6U.png)

## @RestController
* **@RestController** là một phiên bản đặc biệt của controller. Nó bao gồm **@Controller** và **@ResponseBody**, do đó giúp đơn giản hóa việc triển khai Controller
* Sự khác biệt giữa **@RestController** và **@Controller** nằm ở cách xử lý dữ liệu trả về. **@RestController** tự động chuyển đổi đối tượng trả về thành dữ liệu định dạng(JSON/XML), trong khi **@Controller** yêu cầu sử dụng **@ResponseBody** để chỉ định cách xử lý dữ liệu trả về

## @Service
* Khi đánh dấu một lớp bằng **@Service**, nó là nơi xử lý nghiệp vụ của hệ thống và có thể được sử dụng lại ở nhiều nơi

## @Repository
* là annotation cấp class, đại diện cho lớp Data Access Object(DAO), chịu trách nhiệm cho việc truy cập dữ liệu từ cơ sở dữ liệu

## @Configuration
* Lớp được đánh dấu bằng **@Configuration**, nơi định nghĩa ra các Bean, thay thế cho thẻ '< bean />' trong file xml
* Việc sử dụng **@Configuration** cho phép quản lý các Bean trong ứng dụng Spring bằng cách sử dụng mã Java thay vì cấu hình xml <br>
![hi](https://i.imgur.com/EvI3vfO.png)

## @Bean
* **@Bean** tương ứng với phần từ **< bean />** trong XML, được sử dụng để tạo các Spring bean và thường được sử dụng cùng với **Configuration**


# Spring @Autowired Annotation
Annotation **@Autowired** đánh dấu một Constructor, phương thức Setter, thuộc tính và phương thức để tự động inject các bean bằng cơ chế Dependency Injection của Spring <br>
Sau khi tìm thấy một class đánh dấu **@Component** thì quá trình inject Bean xảy ra theo cách như sau:
* Nếu class không có hàm Contructor hay Setter thì sẽ sử dụng **Java Reflection** để đưa đối tượng vào thuộc tính đánh dấu **@Autowire**
* Nếu có hàm **Contructor** thì sẽ inject Bean vào bởi tham số của hàm
* Nếu có hàm **Setter** thì sẽ inject Bean vào bởi tham số của hàm
> Annotation **@Autowired** trên một constructor không còn cần thiết nếu Bean mục tiêu đã định nghĩa chỉ một constructor từ ban đầu. Tuy nhiên, nếu có nhiều constructor khả dụng, ít nhất một trong các constructor đó phải được đánh dấu bằng **@Autowired** để chỉ dẫn cho container biết constructor nào đang sử dụng
### Ví dụ
![hi](https://i.imgur.com/ybYnSjk.png)<br>
![hi](https://i.imgur.com/N8B7Kt5.png)<br>
![hi](https://i.imgur.com/kQHvZ15.png)<br>

> Sử dụng annotation **@Order** nếu muốn các bean được sắp xếp theo một thứ tự cụ thể 
> Việc Autowiring sẽ thất bại khi không có bean nào phù hợp được injection, thì Spring bỏ qua bằng cách đánh dấu nó không bắt buộc đó là thiết lập thuộc tính **required = false**<br>

![hi](https://i.imgur.com/oB6hvl7.png)<br>
![hi](https://i.imgur.com/lckzf4l.png)<br>
![hi](https://i.imgur.com/0Gyu9pF.png)<br>
![ji](https://i.imgur.com/t2Bwgov.png)

# Wiring in Spring: @Resoure, @Inject and @Autowired
### Ví dụ
![k](https://i.imgur.com/lkMnb9Q.png) <br>
Ở ví dụ trên, bean 'person' được tạo cho class Person, trong bean 'customer' chúng ta dùng thuộc tính ref để link tới bean person<br>
Nhưng thực tế thì không cần chỉ rõ biến person trong customer link tới bean nào, Spring Container sẽ tự động tìm bean thích hợp để inject nó vào. Đó chính là Auto-Wiring trong Spring
> Có 5 loại auto-wiring trong Spring:<br>
Auto-wiring **'no'**: áp dụng ở ví dụ trên<br>
Auto-wiring **'byName'**: Spring container tìm bean có id trùng với attribute Person trong class Customer và thực hiện autowired thông qua method setter<br>
Auto-wiring **'byType'**: Spring container tìm bean có type là Person và thực hiện autowired thông qua method setter<br>
Auto-wiring **'constructor'**: Spring container tìm bean có type giống type của Person trong method contructor và thực hiện autowired thông qua method constructor<br>
Auto-wiring **'autodetect'**: Spring Container sẽ thử với auto wired byConstructor, nếu không được thì nó chuyển sang auto wired byType. Tuy nhiên cách này đã không còn sử dụng từ Spring version 3 <br>


## @Resource annotation
Annotation **@Resource** có các cách thức thực thi sau đây, được liệt kê theo mức ưu tiên:
* Match by Name
* Match by Type
* Match by Qualifier<br>
Các cách thức này áp dụng cho cả setter injection và field injection

## @Inject annotation
Annotation **@Inject** có các cách thức thực thi sau đây, được liệt kê theo mức ưu tiên:
* Match by Type
* Match by Qualifier
* Match by Name<br>
Các cách thức này áp dụng cho cả setter injection và field injection

## @Autowired annotation
Annotation **@Autowired** có các cách thức thực thi tương tự **@Inject**, được liệt kê theo mức ưu tiên:
* Match by Type
* Match by Qualifier
* Match by Name<br>
Các cách thức này áp dụng cho cả setter injection và field injection
# Spring @Qualifier Annotation
Annotation **@Qualifier** được dùng cùng với annnotation **@Autowired**, khi cần kiểm soát nhiều hơn quá trình dependency injection. Annotation này được sử dụng để tránh nhầm lẫn xẩy ra khi tạo nhiều bean cùng loại.
## Ví dụ
![hi](https://i.imgur.com/QJXC96c.png)<br>
![hi](https://i.imgur.com/Z430vIQ.png)<br>
![hi](https://i.imgur.com/AweDIaf.png)<br>
> Nếu không sử dụng **@Qualifier**, khi Spring gặp nhiều bean cùng 1 kiểu, nó sẽ không thể xác định rõ ràng bean nào muốn thêm vào, điều này dẫn đến ngoại lệ **NoUniqueBeanDefinitionException**.

![h](https://i.imgur.com/AUbJuOu.png)
# Nguồn
https://www.geeksforgeeks.org/spring-difference-between-inversion-of-control-and-dependency-injection/ <br>
https://mazdagialaii.vn/inversion-of-control-la-gi/ <br>
https://viblo.asia/p/dependency-injection-va-inversion-of-control-Qpmle9Nolrd <br>
https://viblo.asia/p/tong-quan-ve-spring-bean-WR5JRbZ0JGv <br>
https://shareprogramming.net/hieu-ro-spring-bean-scope/ <br>
https://levunguyen.com/laptrinhspring/2020/03/09/pham-vi-hoat-dong-cua-bean/<br>
https://javatechonline.com/spring-boot-annotations-with-examples/ <br>
https://www.tutorialspoint.com/spring/spring_autowired_annotation.htm#:~:text=The%20%40Autowired%20annotation%20provides%20more,names%20and%2For%20multiple%20arguments.<br>
https://techmaster.vn/posts/36165/spring-boot-1-huong-dan-component-va-autowired<br>
https://www.baeldung.com/spring-controller-vs-restcontroller<br>
https://www.geeksforgeeks.org/spring-autowired-annotation/<br>
https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/autowired.html<br>
https://www.baeldung.com/spring-annotations-resource-inject-autowire<br>
https://www.baeldung.com/spring-qualifier-annotation<br>
https://stackjava.com/spring/spring-core-phan-8-autowiring.html<br>
