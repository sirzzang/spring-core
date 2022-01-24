package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * Order 도메인 테스트
 */

public class OrderApp {

    public static void main(String[] args) {
        // 서비스
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        // 회원
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 주문
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order: " + order);
        System.out.println("calculated price: " + order.calculatePrice());

    }

}