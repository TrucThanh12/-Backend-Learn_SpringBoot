# Inversion Of Control là gì?
## Khái niệm cơ bản về Inversion Of Control
IoC là một nguyên tắc thiết kế phần mềm, nó đảo ngược quyền kiểm soát của ứng dụng. Trong truyền thống, các đối tượng xây dựng và quản lý các thực thể khác. Tuy nhiên, khi áp dụng IoC, việc xử lý các khối logic và quyết định được chuyển sang bên ngoài và được quản lý bởi IoC Container. IoC có trách nhiệm xây dựng và quản lý các đối tượng, thay vì đối tượng tự quản lý chính nó
### Ví dụ
![vidu1](image.png) <br>
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
![Alt text](image-2.png)
<br>
Ta có ví dụ sau:
* Class MySqlDAO chuyên thực hiện truy vấn cơ sở dữ liệu MySQL của ứng dụng
* Bây giờ nếu muốn truy vấn tới cơ sở dữ liệu từ Mongo. Thì phải xóa khai báo MySqlDAO và thay bằng MongoDAO, sau đó muốn dùng lại MySqlDAO thì lại phải sửa và test lại nhiều lần 

Để hiểu Dependency Injection một cách đơn giản như sau:
![Alt text](image-1.png)
* Các module không giao tiếp trực tiếp với nhau, mà thông qua interface. Module cấp thấp sẽ implement interface, module cấp cao sẽ gọi module cấp thấp thông qua interface
    * Để giao tiếp với database, ta có interface AbstractDAO, các module cấp thấp là MongoDAO, MySqlDAO. Module cấp cao là Client sẽ chỉ sử dụng interface AbstractDAO
* Việc khởi tạo các module cấp thấp sẽ do DI Container thực hiện
    * Trong module Client, ta sẽ không khởi tạo AbstractDAO db = new MongoDAO(), việc này sẽ do DI Container thực hiện. Module Client sẽ không biết gì về module MongoDAO hay MySqlDAO.
* Việc module nào gắn với interface nào sẽ được config trong code
* DI được dùng để làm giảm sự phụ thuộc giữa các module, dễ dàng hơn trong việc thay đổi module, bảo trì code và testing
## Ví dụ
### Tạo 1 interface để khai báo các method giao tiếp với database
![Alt text](image-3.png)
### Tạo các class DAO tương ứng với từng loại database và implements các method của AbstractDAO
![Alt text](image-5.png)
![Alt text](image-4.png)
![Alt text](image-6.png)
### file config.properites lưu thông tin config quyết định kết nối với database nào
![Alt text](image-7.png)
### Class Client cần dùng đến DAO thì chúng ta sẽ khai báo AbstractDAO
![Alt text](image-8.png)
### Tạo 1 file FactoryDAO để quyết định đối tượng được tạo ra
![Alt text](image-9.png)
### Demo
![Alt text](image-11.png)

## Các phương pháp thực hiện Dependency Injection
* **Contructor Injection**: Các denpendency sẽ được truyền vào 1 class thông qua constructor của class đó. <br>
![Alt text](image-12.png)

* **Setter Injection**: Các dependency sẽ được truyền vào 1 class thông qua các hàm Setter/Getter<br>
![Alt text](image-15.png)

* **Public fields**: Các dependency sẽ được truyền vào 1 class một cách trực tiếp vào các public field. Cách này ít dc sử dụng<br>
![Alt text](image-14.png)

# What is a Spring Bean?

