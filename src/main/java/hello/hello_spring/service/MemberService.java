package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMeberRepository;
import hello.hello_spring.test.CustomConsumer;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMeberRepository();

    /*회원가입*/
    public  Long join(Member member){
        // 같은 이름이 있는 중복 회원은 x.
        //Member result = memberRepository.findByName(member.getName()).orElseThrow(RuntimeException::new);

       /* Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/

        validateDuplicate(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicate(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m ->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /*전체 회원 조회*/
    public List<Member> findMembers(){
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
