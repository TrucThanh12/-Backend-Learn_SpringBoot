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
https://github.com/TrucThanh12/Backend/tree/main/DemoRedis
# Nguồn
https://aws.amazon.com/redis/<br>
https://viblo.asia/p/tim-hieu-tong-quan-ve-redis-bJzKmrV6Z9N<br>
