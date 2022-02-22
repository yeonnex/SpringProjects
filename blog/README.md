현재 Board 모델은 `List<Reply> replys` 로 되어있는 댓글을 가지고 있다. 즉 Board가 Reply 를 들고 있다.

만약 Board 를 findById 하여 select 하게 되면, `id` 와 `title`, `count`, `user`, `reply`,` createDate` 를 DB에서 들고 오게 된다.

![image-20220221171919239](https://raw.githubusercontent.com/yeonnex/image-server/main/img/image-20220221171919239.png)

mappedBy 라는 것이 붙어있는 reply의 경우, 연관관계의 주인이 아니다. 즉 FK가 아니라는 뜻이고, DB의 컬럼에는 없으나 DB를 select할 때 EAGER 전략이므로 같이 당겨오겠다는 의미이다.

정리하면,Board 서비스에서 글 상세보기로 보드를 들고 오면, 보드는 reply를 들고 오게 되어있으니, 댓글을 상세보기 화면에서 뿌려주기 위해 따로 뭔가 할 필요는 없다! 개꿀!

![image-20220221174622145](https://raw.githubusercontent.com/yeonnex/image-server/main/img/image-20220221174622145.png)

하지만, 문제가 하나 있다. 바로 무한참조가 일어난다는 것.

![image-20220221175630804](https://raw.githubusercontent.com/yeonnex/image-server/main/img/image-20220221175630804.png)

Board 를 셀렉하면, 이 안에는 User와 Reply가 있다. User 는 정상적으로 문제없이 잘 가져오지만 Reply 의 경우 문제가 생긴다. 이유는 Reply 모델의 경우, 누가 썼는지에 대한 User 필드가 있어 User 정보도 중복으로 또 가져오는데, 제일 큰 문제는 Board 필드를 가지고 있어 Board 를 또 다시 가져오게 되는데, Board 는 또 Reply 를 가지고 있으므로... 이렇게 계속해서 참조가 생기며 데이터를 무한히 가져오는 무한참조 문제가 발생하게 되는 것이다.

> 이를 어떻게 해결할 수 있을까?

Board 만 가지고 오려고 헸으나, Board 를 셀렉하는 순간 JPA 는 User 정보와 Reply 정보를 가지고 오게 된다. User의 경우 다른 연관관계가 없으므로 User의 정보만 깔끔하게 들고 오고 끝나지만, Reply가 문제이다.

위 test에서, boardRepository.findById(id) 하여 return 할 때, **jackson라이브러리가 발동**하게 된다.