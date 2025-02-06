package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMeberRepositoryTest  {
    MemoryMeberRepository repository = new MemoryMeberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get() ;
        System.out.println("result = " + (result == member) );
        assertThat(member).isEqualTo(result);
    }

    @Test
    public  void findByName(){
        Member member1 = new Member();
        member1.setName("장진욱");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("송영민");
        repository.save(member2);

        Member result = repository.findByName("송영민").get();
         assertThat(result).isEqualTo(member2);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("장진욱");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("송영민");
        repository.save(member2);

       List<Member> result =  repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}
