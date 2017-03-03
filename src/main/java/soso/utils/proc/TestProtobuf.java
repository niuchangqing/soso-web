package soso.utils.proc;

import com.google.protobuf.InvalidProtocolBufferException;

import soso.utils.proc.User.user;

public class TestProtobuf {

	public static void main(String[] args) throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		User.user.Builder builder = User.user.newBuilder();
		builder.setUserId(100);
		builder.setName("niucqing");
		
		user u = builder.build();
		
		byte[] bytes = u.toByteArray();
		
		
		user result = user.parseFrom(bytes);
		System.out.println(result.getUserId());
		System.out.println(result.getName());
	}

}
