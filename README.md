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

Để hiểu Dependency Injection một cách đơn giản như sau:
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
* Một bean được định nghĩa với Singleton Scope sẽ khiến IoC Container khởi tạo duy nhất một instance cho bean đó và nó được sử dụng trong tất cả yêu cầu đến bean này
* Bất kỳ một thay đổi nào trên các singleton scope bean đều sẽ ảnh hưởng đến tất cả những nơi đang tham chiếu đến nó. 
## Prototype
* Một bean với prototype scope sẽ trả về các instance khác nhau mỗi khi có một yêu cầu mới sử dụng chúng đến IoC container
* Hai object tham chiếu đến các Prototype Bean, thì mỗi object trả về từ IoC là khác nhau, do vậy việc chúng ta thay đổi giá trị trên một Prototype bean sẽ không ảnh hưởng đến Prototype bean của object còn lại
## Request
* Request Scope khởi tạo một bean instance cho một HTTP request
* Request Scope đảm bảo rằng mỗi khi có một HTTP Request đến ứng dụng, một instance mới của bean sẽ được tạo ra và được sử dụng trong request đó. Nói cách khác, mỗi request có một bản sao riêng biệt của bean này để làm việc. Đảm bảo rằng các thay đổi trong bean này trong một request không ảnh hưởng đến các request khác.
## Session
* Session Scope khởi tạo một bean instance cho một HTTP Session
* Session Scope cho phép bạn tạo bean và giữ chúng trong 1 phiên làm việc trong ứng dụng web. Điều này có nghĩa là các bean có phạm vi session sẽ tồn tại trong suốt thời gian làm việc của người dùng và sẽ được chia sẻ giữa các request trong cùng 1 phiên làm việc
* Ví dụ, thông tin đăng nhập của người dùng có thể được lưu trữ trong 1 bean với session scope
## Application
* Application Scope khởi tạo một bean instance cho một vồng đời của một ServletContext, nó tương tự như singletion scope nhưng có 1 điểm khác biệt rất quan trọng giữa chúng
* Khi mà application bean sử dụng một instance cho nhiều ứng dụng đang chạy trong cùng 1 SevletContext thì singletion bean được sử dụng trong một application context của 1 ứng dụng nhất định
## WebSocket
* WebSocket Scope khởi tạo cho một WebSocket Session
* WebSocket thường được sử dụng để truyền tài dữ liệu giữa máy khách và máy chủ trong thời gian thực, và việc quản lý trạng thái hoặc thông tin phiên làm việc thường được thực hiện theo cách tùy thuộc vào ứng dụng cụ thể

# Spring Bean Annotations
## @Component
* Nó được sử dụng để đánh dấu một class là một Spring Component. Spring sẽ quét và tạo bean từ class được đánh dấu bởi **@Component** và thêm chúng và container IoC để quản lý
* **Tạo bean tự động**: khi đánh dấu 1 class bằng **@Component**, Spring sẽ tự động tạo 1 bean từ class đó và thêm vào container IoC
* **Quét tự động**: Spring quét các package được chỉ định để tìm và tạo bean từ các class được đánh dấu bởi **@Component** --> tự động phát hiện các bean trong ứng dụng
 
## @Controller
## @RestController
## @Service
## @Repository
## @Configuration
## @Bean
## @Bean vs @Component

# Nguồn
https://www.geeksforgeeks.org/spring-difference-between-inversion-of-control-and-dependency-injection/ <br>
https://mazdagialaii.vn/inversion-of-control-la-gi/ <br>
https://viblo.asia/p/dependency-injection-va-inversion-of-control-Qpmle9Nolrd <br>
https://viblo.asia/p/tong-quan-ve-spring-bean-WR5JRbZ0JGv <br>
