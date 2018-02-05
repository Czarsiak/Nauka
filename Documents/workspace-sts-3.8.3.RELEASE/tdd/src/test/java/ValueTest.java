import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ValueTest {

    @Test
    public void shouldBeOddForOne() throws Exception {
    //given
     Value value = new Value(1);

     //when
     boolean actual = value.isOdd();

     //then
     assertThat(actual).isTrue();
    }

    @Test
    public void shouldNotBeOddForTwo() throws Exception {
        //given
        Value value = new Value(2);

        //when
        boolean actual = value.isOdd();

        //then
        assertThat(actual).isFalse();
    }

    @Test
    public void shouldBeOddForSeven() throws Exception {
        //given
        Value value = new Value(7);

        //when
        boolean actual = value.isOdd();

        //then

        assertThat(actual).isTrue();
    }

    @Test
    public void shouldNOtBeOddForZero() throws Exception {
        //given
        Value value = new Value(0);

        //when
        boolean actual = value.isOdd();

        //then
        assertThat(actual).isFalse();

    }

    @Test
    public void shouldNotBeOddForMinusThree() throws Exception {
        //given
        Value value = new Value(-3);

        //when
        boolean actual = value.isOdd();

        //then
        assertThat(actual).isTrue();
    }

    @Test
    public void shouldThrowsIllegalArgumentException() throws Exception {
        Value value = new Value(null);




        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> value.isOdd())
                .withMessage("Value should not be null!")
                .withNoCause();
    }
}
