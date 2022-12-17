package cn.itcast.order.domain;

import cn.itcast.order.common.CommentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "content")
    private String content;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CommentStatus status;
    @Column(name = "stars")
    private Integer stars;
}
