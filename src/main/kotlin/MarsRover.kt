import Command.BACKWARD
import Command.FORWARD
import Command.LEFT
import Command.RIGHT
import Direction.EAST
import Direction.NORTH
import Direction.SOUTH
import Direction.WEST
import java.util.Scanner

object MarsRover {

    @JvmStatic
    fun main(args: Array<String>) {
        val reader = Scanner(System.`in`)
        println("Insert horizontal map size:")
        val sizex = reader.nextInt()
        println("Insert vertical map size:")
        val sizey = reader.nextInt()
        println("Insert horizontal initial rover position:")
        var roverx = reader.nextInt()
        println("Insert vertical initial rover position:")
        var rovery = reader.nextInt()
        var position = Position(roverx, rovery)
        println("Insert initial rover direction:")
        var direction = Direction.fromChar(reader.next())
        do {
            println("Insert command (f = forward, b = backward, l = turn left, r = turn right):")
            val command = Command.fromChar(reader.next())
            if (command == FORWARD) {
                val c = ForwardCommand()
                position = c.execute(direction, position)
            }
            if (command == BACKWARD) {
                val c = BackwardCommand()
                position = c.execute(direction, position)
            }
            if (command == LEFT) {
                direction = moveLeft(direction)
            }
            if (command == RIGHT) {
                direction = moveRight(direction)
            }
            println(String.format("Rover is at x:%d y:%d facing:%s", position.x, position.y, direction.char))
        } while (true)
    }

    private fun moveRight(direction: Direction) = when (direction) {
        NORTH -> EAST
        EAST  -> SOUTH
        SOUTH -> WEST
        WEST  -> NORTH
    }

    private fun moveLeft(direction: Direction) = when (direction) {
        NORTH -> WEST
        WEST  -> SOUTH
        SOUTH -> EAST
        EAST  -> NORTH
    }
}
