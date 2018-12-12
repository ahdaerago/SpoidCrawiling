package dao;


import javax.print.Doc;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import dto.MongoDTO;

public class MongoDAO {
	// Connection
		MongoClient mClient = new MongoClient("localhost", 27017);
		// System.out.println("접속 완료");

		// Database셀렉
		MongoDatabase helloDB = mClient.getDatabase("test");
		// System.out.println("데이터베이스명 : " + db.getName());

		// 테이블생성 & 사용
		MongoCollection<Document> collection = helloDB.getCollection("now_naver_review");
		
		

		
		static Block<Document> printBlock = new Block<Document>() {
			@Override
			public void apply(final Document document) {
				System.out.println(document.toJson());
			}
		};
		
		public void dropmongo() {
			try {
				// table drop
				collection.drop();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				mClient.close();// 연결해제
			}
		}
		
		
		
		public void insetmongo(MongoDTO mDto) {
			try {

				Document doc = new Document("code", mDto.getCode())
						.append("review_code", mDto.getReview_code())
						.append("title", mDto.getTitle())
						.append("review", mDto.getReview())
						.append("score", mDto.getScore())
						.append("writer", mDto.getWriter())
						.append("date", mDto.getrdate());
				
				
				collection.insertOne(doc);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				mClient.close();// 연결해제
			}

		}

		public void mongosearch() {

			try {

				// Iterable = collection(배열)에 들어있는 뭉텅이를 하나하나 찟어 방에 넣는것
				FindIterable<Document> iterate = collection.find();
				// iterator = 하나씩 찟어준것을 호출 가지고 오는 것
				MongoCursor<Document> cursor = iterate.iterator();
				// 출력
				while (cursor.hasNext()) {
					Document document = cursor.next();
					String JsonResult = document.toJson();
					System.out.println(JsonResult);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				mClient.close();
			}

		}
		public String checkdnewest(String code) {
			String date = null;
			try {
			
				//find()메소드에 조건 추가하기
				BasicDBObject query = new BasicDBObject(); // select where 절과 같이 연산자를 주기 위해서 선언하는 객체
				query.put("code",code);
				//영화 코드 가장 최근 댓글 추출
				FindIterable<Document> iterate = collection.find(query).sort(new BasicDBObject("$natural",1)).limit(1);
				
				MongoCursor<Document> cursor = iterate.iterator();
				System.out.println("--------가장 최근 날짜는?-------");
				
				System.out.println(cursor.hasNext());
				if(cursor.hasNext() == false) {
					System.out.println("가랏");
					code = ""; 
					return code;
				}else {
					while(cursor.hasNext()) {
						Document document = cursor.next();
						String JsonResult = document.toJson();
						code = (String)document.get("review_code");
		
					}
				}
				System.out.println("코드 : "+code);
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("데이터 없음!");
			}finally {
				mClient.close();
			
			}
			return code;
			
		}
}
