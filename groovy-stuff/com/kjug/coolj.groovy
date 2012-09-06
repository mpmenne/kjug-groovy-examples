import org.codehaus.groovy.scriptom.*
import static org.codehaus.groovy.scriptom.tlb.sapi.SpeechVoiceSpeakFlags.*
import static org.codehaus.groovy.scriptom.tlb.sapi.SpeechRunState.*
import org.codehaus.groovy.scriptom.tlb.sapi.SpeechLib

Scriptom.inApartment
{
  def voice = new ActiveXObject('SAPI.SpVoice')

  voice.speak """
    Power and the money, money and the power
    Minute after minute, hour after hour
    Everybody's runnin, but half of them ain't lookin
    It's goin on in the kitchen, but I don't know what's cookin
    They say I got ta learn, but nobody's here to teach me
    If they cant understand, how can they reach me?
    I guess they cain't -- I guess they won't
    I guess they front; that's why I know my life is outta luck, fool

    Tell me why are we ... so blind to see ...
    That the ones we hurt ... are you and me ...
    Tell me why are we ... so blind to see ...
    That the ones we hurt ... are you and me ...
  """
}
