import re
import sys
from pathlib import Path

def markdown_to_html(markdown_text):
    """Convert basic markdown to HTML"""
    html = markdown_text
    
    # Headers
    html = re.sub(r'^# (.+)$', r'<h1>\1</h1>', html, flags=re.MULTILINE)
    html = re.sub(r'^## (.+)$', r'<h2>\1</h2>', html, flags=re.MULTILINE)
    html = re.sub(r'^### (.+)$', r'<h3>\1</h3>', html, flags=re.MULTILINE)
    html = re.sub(r'^#### (.+)$', r'<h4>\1</h4>', html, flags=re.MULTILINE)
    
    # Bold and italic
    html = re.sub(r'\*\*(.+?)\*\*', r'<strong>\1</strong>', html)
    html = re.sub(r'\*(.+?)\*', r'<em>\1</em>', html)
    
    # Code blocks
    html = re.sub(r'```(\w+)?\n(.+?)```', r'<pre><code class="language-\1">\2</code></pre>', html, flags=re.DOTALL)
    
    # Inline code
    html = re.sub(r'`([^`]+)`', r'<code>\1</code>', html)
    
    # Links
    html = re.sub(r'\[([^\]]+)\]\(([^)]+)\)', r'<a href="\2">\1</a>', html)
    
    # Images
    html = re.sub(r'!\[([^\]]*)\]\(([^)]+)\)', r'<img src="\2" alt="\1">', html)
    
    # Horizontal rules
    html = re.sub(r'^---+$', '<hr>', html, flags=re.MULTILINE)
    html = re.sub(r'^\*\*\*+$', '<hr>', html, flags=re.MULTILINE)
    
    # Unordered lists
    def replace_ul(match):
        items = re.findall(r'^- (.+)$', match.group(0), flags=re.MULTILINE)
        list_items = '\n'.join([f'  <li>{item}</li>' for item in items])
        return f'<ul>\n{list_items}\n</ul>'
    
    html = re.sub(r'((^- .+$\n)+)', replace_ul, html, flags=re.MULTILINE)
    
    # Ordered lists
    def replace_ol(match):
        items = re.findall(r'^\d+\. (.+)$', match.group(0), flags=re.MULTILINE)
        list_items = '\n'.join([f'  <li>{item}</li>' for item in items])
        return f'<ol>\n{list_items}\n</ol>'
    
    html = re.sub(r'((^\d+\. .+$\n)+)', replace_ol, html, flags=re.MULTILINE)
    
    # Paragraphs (wrap text that's not already in HTML tags)
    lines = html.split('\n')
    processed_lines = []
    in_paragraph = False
    
    for line in lines:
        stripped = line.strip()
        if not stripped:
            if in_paragraph:
                processed_lines.append('</p>')
                in_paragraph = False
        elif re.match(r'^<h|<ul|<ol|<pre|<hr|<blockquote', stripped):
            if in_paragraph:
                processed_lines.append('</p>')
                in_paragraph = False
            processed_lines.append(line)
        elif re.match(r'^<', stripped):
            processed_lines.append(line)
        else:
            if not in_paragraph:
                processed_lines.append('<p>')
                in_paragraph = True
            processed_lines.append(line)
    
    if in_paragraph:
        processed_lines.append('</p>')
    
    html = '\n'.join(processed_lines)
    
    # Line breaks
    html = html.replace('\n', '<br>\n')
    
    return html

def main():
    # Read the markdown file
    markdown_path = Path(__file__).parent / 'Notes.md'
    
    if not markdown_path.exists():
        print(f"Error: {markdown_path} not found!")
        sys.exit(1)
    
    with open(markdown_path, 'r', encoding='utf-8') as f:
        markdown_content = f.read()
    
    # Convert to HTML
    html_content = markdown_to_html(markdown_content)
    
    # Create the full HTML page with styling
    full_html = f'''<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Learning Notes</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/styles/github-dark.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/highlight.min.js"></script>
    <style>
        * {{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }}

        body {{
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
            line-height: 1.6;
        }}

        .container {{
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            border-radius: 15px;
            padding: 40px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
        }}

        h1 {{
            color: #667eea;
            border-bottom: 3px solid #667eea;
            padding-bottom: 15px;
            margin-bottom: 30px;
            font-size: 2.5em;
        }}

        h2 {{
            color: #764ba2;
            margin-top: 40px;
            margin-bottom: 20px;
            font-size: 1.8em;
            border-left: 4px solid #764ba2;
            padding-left: 15px;
        }}

        h3 {{
            color: #667eea;
            margin-top: 25px;
            margin-bottom: 15px;
            font-size: 1.4em;
        }}

        p {{
            color: #333;
            margin-bottom: 15px;
        }}

        ul, ol {{
            margin-left: 30px;
            margin-bottom: 20px;
        }}

        li {{
            color: #555;
            margin-bottom: 8px;
        }}

        code {{
            background: #f5f5f5;
            padding: 2px 6px;
            border-radius: 4px;
            font-family: 'Courier New', monospace;
            font-size: 0.9em;
            color: #d63384;
        }}

        pre {{
            background: #2d2d2d;
            border-radius: 8px;
            padding: 20px;
            overflow-x: auto;
            margin: 20px 0;
        }}

        pre code {{
            background: none;
            padding: 0;
            color: #f8f8f2;
        }}

        blockquote {{
            border-left: 4px solid #667eea;
            padding-left: 20px;
            margin: 20px 0;
            color: #666;
            font-style: italic;
        }}

        img {{
            max-width: 100%;
            border-radius: 8px;
            margin: 20px 0;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }}

        table {{
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }}

        th, td {{
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }}

        th {{
            background: #667eea;
            color: white;
        }}

        tr:nth-child(even) {{
            background: #f9f9f9;
        }}

        hr {{
            border: none;
            border-top: 2px solid #667eea;
            margin: 30px 0;
        }}

        a {{
            color: #667eea;
            text-decoration: none;
        }}

        a:hover {{
            text-decoration: underline;
        }}

        @media (max-width: 768px) {{
            .container {{
                padding: 20px;
            }}

            h1 {{
                font-size: 2em;
            }}

            h2 {{
                font-size: 1.5em;
            }}
        }}
    </style>
</head>
<body>
    <div class="container">
        {html_content}
    </div>

    <script>
        // Initialize syntax highlighting
        hljs.highlightAll();
    </script>
</body>
</html>'''

    # Write the HTML file
    output_path = Path(__file__).parent / 'index.html'
    with open(output_path, 'w', encoding='utf-8') as f:
        f.write(full_html)
    
    print('Markdown converted to HTML successfully!')
    print(f'Output file: {output_path}')

if __name__ == '__main__':
    main()
