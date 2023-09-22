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

# Tìm hiểu về MongoDB
## Ưu điểm
* Ít schema hơn: vì schema được sỉnh ra là để nhóm các đối tượng vào 1 cụm, dễ quản lý. Ví dụ như tạo 1 schema tên là Students thì chỉ có những gì liên quan đến student thì mới được cho vào schema này. Trong khi đó mongodb thì chỉ 1 collection ta có thể chứa nhiều document khác nhau, với mỗi số trường, nội dung, kích thước lại có thể khác nhau
* Không có các join phức tạp
* Khả năng mở rộng cực lớn: việc mở rộng dữ liệu không cần lo đến các vấn đề như khóa ngoại, khóa chính, kiểm tra ràng buộc ... 

## Nhược điểm
* Đôi khi sẽ tốn bộ nhớ do dữ liệu lưu ở dạng key-value nên những Collection chỉ khác về value mà có phần giống về key. Và vì không hỗ trợ join như RDBMS nên sẽ có tình trạng dư thừa dữ liệu

# Caching là gì?
* Caching là một kỹ thuật tăng độ truy xuất dữ liệu và giảm tải cho hệ thống. Cache là nơi lưu tập hợp các dữ liệu, thường có tính chất nhất thời, cho phép sử dụng lại dữ liệu đã lấy hoặc tính toán trước đó, nên sẽ giúp tăng tốc cho việc truy xuất dữ liệu ở những lần sau.
> Caching là hành động lưu trữ lại kết quả của action tốn thời gian để phục vụ việc sử dụng lại nhanh hơn
* Bộ nhớ có dạng key-value do đó operation cơ bản với cache sẽ gồm:
    * Get
    * Set
    * Delete
* 1 value được lưu vào cache thường đi kèm với tham số TTL - Time To Live, tức là hạn sử dụng để loại bỏ value này khỏi cache,giải phóng dung lượng cho cache.
* Vấn đề chung mà mọi loại cache phải xử lý: **stale data**, tức là data không đúng với data gốc
* Tiêu chí để xem xét có cần cache cái này không:
    * Tốn nhiều time
    * Kết quả dùng lại được nhiều lần


## Làm sao để data trong cache (redis) luôn đúng với database(mysql, mongodb)
Người ta thường dùng các cơ chế write-xxx cache như sau:
* **Write-around**: Application sẽ update data vào DB trước tiên, sau đó cache mới được update. Đây là cách thông dụng nhất và đáp ứng đa số ứng dụng khi chúng ta cần đảm bảo được lưu trữ chuẩn xác trong DB. Người ta thường sử dụng 1 processphias sau để refresh cache sau khi action write xảy ra
* **Write-through**: Application sẽ update cache và DB cùng 1 lúc. Kiểu này làm action write kéo dài hơn write-around nhưng được cái vừa đảm bảo dữ liệu được ghi vào DB vừa đảm bảo data trong cache luôn tươi mới
* **Write-behind**: Application sẽ updatde vào cache trước, sau đó mới update data và DB. Cách này là cách handle những ứng dụng có lượng write lớn và cho phép rủi ro liên quan tới tính toàn vẹn dữ liệu do data có thể mất mát khi chưa được lưu vào DB. Tuy nhiên đây là loại có action nhanh nhất<br>
![hi](https://i.imgur.com/CeCP63R.png)
# Redis là gì
Redis là tên viết tắt của Remote Dictionary Server, là kho dữ liệu key-value, trong bộ nhớ, mã nguồn mở và có tốc độ truy cập nhanh để dùng làm cơ sở dữ liệu, cache, message broker và queue. <br>
Thông thường khi ta sử dụng cơ sở dữ liệu thì vẫn lưu trữ trên ổ đĩa cứng. Ví dụ khi query một cơ sở dữ liệu để đọc 10.000 bản ghi. Nếu dữ liệu được lưu trữ trên đĩa, thì nó sẽ mất trung bình 30 giây, trong khi chỉ mất khoảng 0,0002 giây để đọc từ RAM, đó chính là lí do mà Redis dùng RAM để lưu trữ. <br>
Redis thường được tạo trên một máy chủ riêng hoặc set giới hạn bộ nhớ nhất định được sử dụng trên máy chủ dùng chung cho nên sẽ không bị tình trạng thiếu RAM khi chạy đồn thời các ứng dụng khác.<br>
Tuy nhiên, khi lưu trữ trên RAM thì nhanh thật nhưng lại gặp 1 vấn đề là bị mất điện thì dữ liệu cũng mất tiêu. Để ngăn chặn việc mất dữ liệu xảy ra, có một mô-đun được tích hợp sẵn để ghi trạng thái trong bộ nhớ vào file trên đĩa trong những trường hợp nhất định. Các file này được tải lại khi khởi động lại redis. Vì vậy, dữ liệu sẽ không bị mất. Ngoài ra, để tăng tính sẵn sàng và khả năng chịu lỗi của hệ thống.
## Ưu điểm
### Cấu trúc dữ liệu linh hoạt
Redis có nhiều cấu trúc dữ liệu khác nhau nên đáp ứng được nhu cầu ứng dụng của bạn.
### Đơn giản và dễ sử dụng
Redis đơn giản hóa code của bạn bằng cách cho phép bạn viết ít dòng code hơn để lưu trữ, truy cập và sử dụng dữ liệu trong các ứng dụng của mình. Ví dụ: nếu ứng dụng của bạn có dữ liệu được lưu dạng hashmap và bạn muốn lưu trữ dữ liệu đó trong DB thì bạn chỉ cần sử dụng cấu trúc dữ liệu hashes để lưu chúng. Thao tác tương tự trên DB không có cấu trúc dữ liệu hashes sẽ yêu cầu nhiều dòng code để chuyển đổi từ định dạng này sang định dạng khác. Với mỗi kiểu dữ liệu thì Redis thêm nhiều tùy chọn để thao tác và tương tác với dữ liệu của bạn.
### Độ khả dụng và quy mô linh hoạt
Redis có kiến trúc primary-replica theo cấu trúc liên kết dạng một nút chính hoặc cụm. Kiến trúc này cho phép bạn xây dựng những giải pháp có độ khả dụng cao, đảm bảo hiệu suất ổn định và tin cậy. Khi cần điều chỉnh kích thước cụm, bạn có nhiều tùy chọn khác nhau để thay đổi quy mô theo chiều dọc và thay đổi quy mô theo chiều ngang. Việc này cho phép tăng cụm theo nhu cầu của bạn.
### Khả năng mở rộng
Redis là dự án mã nguồn mở đã có cộng đồng lớn tin dùng. Không có giới hạn về nhà cung cấp hoặc công nghệ vì Redis được có tính tiêu chuẩn mở, hỗ trợ các định dạng dữ liệu mở và có tập hợp máy khách phong phú.


# Có những kiểu dữ liệu nào trong Redis
## Strings
String là kiểu dữ liệu cơ bản nhất của Redis. Redis string được lưu dưới dạng nhị phân nên có thể chứa bất kì loại dữ liệu nào như ảnh dạng JPEG hay Ruby object. Độ dài giá trị của 1 string tối đa là 512MB.<br>
![hi](https://i.imgur.com/VM24Dt4.png) <br>
Bằng cách sử dụng lệnh SET và lệnh GET chúng ta sẽ đặt gía trị và truy xuất giá trị của key. Lưu ý rằng SET sẽ thay thế bất kỳ giá trị hiện có nào đã được lưu trữ trong key, trong trường hợp key đó đã tồn tại, ngay cả khi key được liên kết với giá trị không phải là string

## Lists
Kiểu dữ liệu list trong redis chỉ đơn giản là danh sách các string được sắp xếp theo thứ tự chèn vào. Ta có thể thêm các phần tử vào đầu hoặc cuối 1 list sử dụng LPUSH hoặc RPUSH.<br>
Một list được tạo ra khi chúng ta thực hiện thao tác LPUSH hoặc RPUSH với một key rỗng. Tương tự, key sẽ được xóa khỏi key space khi các thao tác trên biến danh sách trở về rỗng. <br>
![hi](https://i.imgur.com/ocmCKvx.png)

## Sets
Set là tập hợp các string không được sắp xếp. Set hỗ trợ các thao tác thêm phần tử, đọc, xóa từng phần tử, kiểm tra sự xuất hiện của phần tử trong tập hợp với thời gian mặc định là O(1) bất kể số lượng phần tử của set đó là bao nhiêu.<br>
![hi](https://i.imgur.com/AX95iou.png)
## Hashes
Hash là kiểu dữ liệu lưu trữ hash table của các cặp key-value, trong đó, key được sắp xếp ngẫu nhiên, không theo thứ tự nào cả. Hash thường được sử dụng để lưu các object (user có các trường name, age, address,...). Mỗi hash có thể lưu trữ 2^32 - 1 cặp key-value. Redis hỗ trợ các thao tác thêm, đọc, xóa từng phần tử, cũng như đọc tất cả giá trị trong hash<br>
![hi](https://i.imgur.com/aJvDs26.png)
## Sorted set
Là 1 tập hợp các string không lặp lại, trong đó mỗi phần tử là map của 1 string (member) và 1 floating-point number (score), danh sách được sắp xếp theo score này, các phần tử là duy nhất, các score có thể lặp lại. Với Zset thì ta có thể thao tác thêm, sửa, xóa phần tử một cách rất nhanh<br>
![hi](https://i.imgur.com/1pZlU1W.png)

# Trường hợp sử dụng Redis

# Dùng Redis trong spring


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
https://www.mongodb.com/compare/mongodb-mysql<br>
