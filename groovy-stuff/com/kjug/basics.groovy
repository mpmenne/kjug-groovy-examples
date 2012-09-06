//just some numbers
assert(1 + 2 == 3)
assert(3 - 2 == 1)
assert 4 * 5 == 20
assert 10/2 == 5
assert 10/4 == 2.5
assert 2**3 == 8
assert 2**-2 == 0.25 // i.e., 1/(2*2) = 1/4

assert 3 == 1.plus(2)
assert 1 == 3.minus(2)
assert 20 == 4.multiply(5)
assert 5 == 10.div(2)
assert 1 == 10.mod(3)

assert 5 > 3
assert 4 >= 4
assert 3 < 5
assert 3 <= 6
assert 7 == 7
assert 7 != 8

def p = 1
assert 1 == p++
assert 3 == ++p

def x = ""
3.times { x += "Hello" }
assert x == "HelloHelloHello"

def total = 0
1.upto(3) { total += it }
assert total == 1 + 2 + 3

def countDown = []
5.downto 1, { countDown << "$it ..." }
assert countDown == ['5 ...', '4 ...', '3 ...', '2 ...', '1 ...']

def odds = []
(1..10).step(2) { odds << it }
assert odds == [1, 3, 5, 7, 9]


//java way
System.out.println("Hello there");
//groovy way
// .... no parenthesis or semi-colons
print "Heyo"

//declare variables
//java way ... static typing
String javaName = "John";
//groovy way ... duck typing
def groovyName = "John"

//java
System.out.println("hey this is todays date " + new Date() + " fool")
//groovy
println "hey this todays date: ${new Date()} fool"

//java
System.out.println("Wow.  Multi \n line \n strings are such a \n pain in the butt");
//groovy
println """
    These multi
            line
                    strings
                                are
                                            so
                                                    easy!!!!!
"""

//java way
List<String> javaFolk = new ArrayList<String>();
javaFolk.addAll("Ted", "Marshall", "Lilly")
javaFolk.add("Stinson")
//groovy way
def groovyFolk = ["Ted", "Marshall", "Lilly"]
groovyFolk << "Stinson"

//java way
for (String name: javaFolk) {
    System.out.println(name)
}
//groovy way
groovyFolk.each{name ->
    print name
}

//java way
for (int i = 0; i < 3; i++) {
    System.out.println("it hurts");
}
//groovy way
3.times {
    println()
}

//lots of groovy stuff too cool for even Java
println groovyFolk.reverse()
println groovyFolk.pop()
//we can even do this stuff to Java Lists
println javaFolk.join("...")

javaFolk.findAll { it.startsWith("M") }

//lists in lists ... we don't care!!!!
def resume = ["Mike", "Menne", ["Java Software Engineer", "Coding Hipster"]]
println resume.flatten()

//maps  ... just like Python!!!!!
def groovyMap = [:]
Map<String, String> javaMap = new HashMap<String, String>();

def easyGroovyMap = ["this can be a key": "this can be a value", 1:"this works too", ]
easyGroovyMap.each { println "you give me ${it.key} and I give you ${it.value}" }


//ranges
println()
println()
def eightToFive = 8..5
eightToFive.each{ print it }
println()
def fiveToEight = 5..8
fiveToEight.each{ print it }
println()


//closures
def convertToCelsius = {
    return (0.5/9.0) *  (it.toFloat() - 32.0)
}
[0, 32, 70, 100].each {
    println("degrees in fahrenheit ${it} and degrees in celsius ${convertToCelsius(it )}")
}

 //java way]
import java.lang.String;

private class BigHeavyJavaClass {
    private String javaAttribute;

    public String getSomeAttribute() {
        return javaAttribute;
    }

    public void setSomeAttribute(String someAttribute) {
        this.javaAttribute = someAttribute;
    }
}
//groovy way
// no getters, setters, import statements,
private class SleekGroovyClass {
    String groovyAttribute
}

//java way
BigHeavyJavaClass javaObject = new BigHeavyJavaClass();
javaObject.setSomeAttribute("to some value");
System.out.println(javaObject.getSomeAttribute());
//groovy way
def groovyObject = new SleekGroovyClass()
groovyObject.groovyAttribute = "so easy"
print groovyObject.groovyAttribute

//null pointer exceptions
//java way
BigHeavyJavaClass javaObjectWithNoAttribute = new BigHeavyJavaClass();
javaObjectWithNoAttribute.setSomeAttribute(null);
//the following would totally blow up
//javaObjectWithNoAttribute.getSomeAttribute().length()
//groovy way
def groovyObjectAndItDontMatter = new BigHeavyJavaClass()
groovyObjectAndItDontMatter.someAttribute = null
groovyObjectAndItDontMatter.someAttribute?.length()

//files
println()
new File(".").eachFile{ file ->
    if (file.isFile())
        println file
}
new File(".").eachFileRecurse {file ->
    println file
}
def file = new File("hello.txt")
file.write("jah this is soooo easy")
println file.text
file <<
    """
        lets add on just a bit more
    """
println file.text


//hacking microsoft excel
import org.codehaus.groovy.scriptom.Scriptom
import org.codehaus.groovy.scriptom.ActiveXObject
import org.codehaus.groovy.scriptom.util.office.ExcelHelper
import org.codehaus.groovy.scriptom.tlb.office.excel.Excel
import org.codehaus.groovy.scriptom.SafeArray
import java.text.SimpleDateFormat

Scriptom.inApartment {

    def helper = new ExcelHelper()
    helper.process(new File("worksheet.xlsx"))
    {workbook ->
      def worksheet = workbook.Sheets.Item['HeckYeah_Sheet']
      assert worksheet.supportsInterface(Excel._Worksheet)

      SafeArray a = worksheet.UsedRange.Value

      a.bounds[0].each
      {row->
        print "\t"
        a.bounds[1].each
        {col->
          if(a[row,col] instanceof String)
            print "[${a[row,col]}]".center(10)
          else if(a[row,col] instanceof Date)
          {
            def f = new SimpleDateFormat('MM/yyyy')
            print f.format(a[row,col]).center(10)
          }
          else
            print a[row,col].toString().center(10)
        }
        println()
      }
    }
}

//meta programming
import java.text.NumberFormat;

Number.metaClass.asCurrency = {
    NumberFormat nf = NumberFormat.getCurrencyInstance()
    nf.format(delegate)
}

Number.metaClass.asCurrency = { Locale loc ->
    NumberFormat nf = NumberFormat.getCurrencyInstance(loc)
    nf.format(delegate)
}


def amount = 123456.7890
assert amount.asCurrency() == '$123,456.79'
assert amount.asCurrency(Locale.FRANCE) == '123 456,79 €'


import org.codehaus.groovy.scriptom.Scriptom
import org.codehaus.groovy.scriptom.ActiveXObject
import javax.swing.JFrame
import org.apache.batik.util.gui.JErrorPane
import javax.swing.JPanel

/**
 * Created by IntelliJ IDEA.
 * User: MPMENN
 * Date: 2/19/12
 * Time: 2:59 PM
 * To change this template use File | Settings | File Templates.
 */


class OutlookScanner {

    public static Integer count = 0;

    def scan() {
        Scriptom.inApartment {
            def outlook = new ActiveXObject("Outlook.Application")
            def namespace = outlook.GetNamespace("MAPI") // There is only "MAPI"

            def inbox = namespace.GetDefaultFolder(6)
            def mails = inbox.Items

            count = mails.Count
        }
    }

    def scanForAttachments() {
        Scriptom.inApartment {
            def outlook = new ActiveXObject("Outlook.Application")
            def namespace = outlook.GetNamespace("MAPI") // There is only "MAPI"

            def inbox = namespace.GetDefaultFolder(6)
            def mails = inbox.Items
            count = 0;

            for (i in 1..mails.Count.value) {
                def mail = mails.Item( i )
                if (mail.Attachments.Count > 0) {
                    println("wohoooo!!! found some attachments")
                }
            }
        }
    }
}

//scans your email faster than outlook
def outlookScanner = new OutlookScanner()
println outlookScanner.scan()

import org.codehaus.groovy.scriptom.*
import static org.codehaus.groovy.scriptom.tlb.sapi.SpeechVoiceSpeakFlags.*
import static org.codehaus.groovy.scriptom.tlb.sapi.SpeechRunState.*

//Definitive proof that you CAN talk and chew gum at the same time.
Scriptom.inApartment
{
  def voice = new ActiveXObject('SAPI.SpVoice')

  //This runs synchronously.
  voice.speak "Hello, GROOVY world!"

  //This runs asynchronously.
  voice.speak """
    We've been spending most our lives
    Living in the Gangsta's Paradise
    We've been spending most our lives
    Living in the Gangsta's Paradise
    We keep spending most our lives
    Living in the Gangsta's Paraside
    We keep spending most our lives
    Living in the Gangsta's Paraside
  """
}